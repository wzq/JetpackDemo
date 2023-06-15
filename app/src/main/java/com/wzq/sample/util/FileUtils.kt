package com.wzq.sample.util

import android.content.Context
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKey
import java.io.File

/**
 * create by wzq on 2023/6/15
 *
 */
object FileUtils {

    fun writeEncryptedFile(context: Context, file: File, str: String) {
        try {
            val masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()

            val encryptedFile = EncryptedFile.Builder(
                context, file, masterKey, EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
            ).build()

            // File cannot exist before using openFileOutput
            if (file.exists()) {
                file.delete()
            }

            encryptedFile.openFileOutput().bufferedWriter().use { buffer ->
                buffer.appendLine(str)
                buffer.flush()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun readEncryptedFile(context: Context, file: File) = runCatching {
        val masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()

        val encryptedFile = EncryptedFile.Builder(
            context, file, masterKey, EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()

        encryptedFile.openFileInput().bufferedReader().use {
            it.readText()
        }
    }
}