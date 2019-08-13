#
# Dockerfile for Cloud Run; contains both the build and launch sections for a Scala backend.
#

# Use an 'sbt' image with Scala 2.12 / 2.13 preloaded.
#
# Note: You can fairly easily derive your own, optimized build Docker base image. See -> https://github.com/akauppi/sbt-scala-builder
#

# tbd. Bring in sbt-scala-builder image
FROM launcher.gcr.io/google/openjdk8 as builder

# --- start of sbt setup (basic part, not optimized for build times!!!) ---
WORKDIR /build
RUN useradd -ms /bin/bash user
RUN chown -R user /build

ARG SBT_VER=1.2.8
ARG SBT_SHA=f4b9fde91482705a772384c9ba6cdbb84d1c4f7a278fd2bfb34961cd9ed8e1d7
ARG _URL=https://github.com/sbt/sbt/releases/download
ARG _OUT=out.zip

RUN apt-get update -qqy \
  && apt-get install -qqy curl \
  && mkdir -p /usr/share \
  && curl -fsSL -o ${_OUT} "${_URL}/v${SBT_VER}/sbt-${SBT_VER}.zip" \
  && echo ${SBT_SHA} ${_OUT} | sha256sum -c - \
  && unzip -qq ${_OUT} \
  && rm -f ${_OUT} \
  && mv sbt "/usr/share/sbt-${SBT_VER}" \
  && ln -s "/usr/share/sbt-${SBT_VER}/bin/sbt" /usr/bin/sbt \
  && apt-get remove -qqy --purge curl \
  && rm /var/lib/apt/lists/*_*

ENTRYPOINT ["/usr/bin/sbt"]

USER user
# --- end of sbt setup ---

# IMPORTANT: Keep this named the same as the project in 'build.sbt'.
ENV _PROJ backend

# Copy local code to the container image
WORKDIR /build
COPY backend .

# Build a release artifact.
RUN sbt stage

#--- Run ---
# Use the Official OpenJDK image for a lean production stage of our multi-stage build
# https://hub.docker.com/_/openjdk
# https://docs.docker.com/develop/develop-images/multistage-build/#use-multi-stage-builds
#
# Note: Any JRE 8+ is fine by Scala
FROM openjdk:8-jre-alpine

# Copy the executables from the builder stage
# Note: '--chown' trick from https://www.codemunity.io/tutorials/dockerising-akka-http/
#
COPY --from=builder --chown=daemon:daemon /app/target/universal/stage /app

# Service must listen to '$PORT' environment variable (default for local; just ignore).
ENV PORT 8080

WORKDIR /app
USER daemon

# Note: 'exec' makes the container respond to Ctrl-C (even without '--init').
#
CMD exec bin/${_PROJ}
