package com.michaldrabik.data_remote.trakt.auth

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.util.Base64
import com.michaldrabik.data_remote.Config
import java.security.MessageDigest
import java.security.SecureRandom
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@SuppressLint("ApplySharedPref")
@Singleton
class TraktPkceProvider @Inject constructor(
  @Named("networkPreferences") private val sharedPreferences: SharedPreferences,
) {

  companion object {
    private const val KEY_CODE_VERIFIER = "TRAKT_PKCE_CODE_VERIFIER"
    private const val VERIFIER_BYTES = 32
    private const val BASE64_FLAGS = Base64.URL_SAFE or Base64.NO_PADDING or Base64.NO_WRAP
  }

  fun createAuthorizeUrl(): String {
    val verifier = ByteArray(VERIFIER_BYTES)
      .also { SecureRandom().nextBytes(it) }
      .let { Base64.encodeToString(it, BASE64_FLAGS) }

    sharedPreferences
      .edit()
      .putString(KEY_CODE_VERIFIER, verifier)
      .commit()

    val challenge = MessageDigest
      .getInstance("SHA-256")
      .digest(verifier.toByteArray(Charsets.US_ASCII))
      .let { Base64.encodeToString(it, BASE64_FLAGS) }

    return "${Config.TRAKT_AUTHORIZE_URL}&code_challenge=$challenge&code_challenge_method=S256"
  }

  fun consumeCodeVerifier(): String? {
    val verifier = sharedPreferences.getString(KEY_CODE_VERIFIER, null)
    sharedPreferences
      .edit()
      .remove(KEY_CODE_VERIFIER)
      .commit()
    return verifier
  }
}
