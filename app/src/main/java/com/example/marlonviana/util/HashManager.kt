package com.example.marlonviana.util

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class HashManager {
    companion object{
        fun makeHashHmd5(): String{
            val s = getTimestamp()+KEY_API_PRIVATE+ KEY_API_PUBLIC

            val MD5 = "MD5"
            try {
                // Create MD5 Hash
                val digest: MessageDigest = MessageDigest
                    .getInstance(MD5)
                digest.update(s.toByteArray())
                val messageDigest: ByteArray = digest.digest()

                // Create Hex String
                val hexString = StringBuilder()
                for (aMessageDigest in messageDigest) {
                    var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                    while (h.length < 2) h = "0$h"
                    hexString.append(h)
                }
                return hexString.toString()
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            }
            return ""
        }

        fun getTimestamp():String{
            var tsLong = System.currentTimeMillis() / 1000
            return tsLong.toString()
        }
    }
}