package backend

import com.google.firebase.auth.FirebaseToken

/*
* Separate class so that we don't carry the Firebase (Java) classes too far (e.g. 'null' is a valid return
* value for some fields; we can turn that to 'Option's).
*/
class UserInfo private (fbt: FirebaseToken) {
  val displayName: String = fbt.getName
  val email: String = fbt.getEmail
  val uid: String = fbt.getUid
}

object UserInfo {
  def apply(fbt: FirebaseToken): UserInfo = new UserInfo(fbt)
}
