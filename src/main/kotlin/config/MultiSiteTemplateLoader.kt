package com.example.config

import freemarker.cache.ClassTemplateLoader
import freemarker.cache.TemplateLoader
import freemarker.cache.MultiTemplateLoader
import java.io.InputStreamReader
import java.io.Reader
import java.net.URL

class MultiSiteTemplateLoader : TemplateLoader {
    private val sharedLoader = ClassTemplateLoader(this::class.java.classLoader, "templates/shared")
    private val siteLoaders = mutableMapOf<String, TemplateLoader>()

    override fun findTemplateSource(name: String): Any? {
        val site = extractSiteFromTemplateName(name)

        val siteLoader = siteLoaders.getOrPut(site) {
            ClassTemplateLoader(this::class.java.classLoader, "templates/$site")
        }

        val multiLoader = MultiTemplateLoader(arrayOf(siteLoader, sharedLoader))
        return multiLoader.findTemplateSource(name)
    }

    override fun getLastModified(templateSource: Any): Long {
        return when (templateSource) {
            is URL -> templateSource.openConnection().lastModified
            else -> -1
        }
    }

    override fun getReader(templateSource: Any, encoding: String): Reader {
        return when (templateSource) {
            is URL -> InputStreamReader(templateSource.openStream(), encoding)
            else -> throw IllegalArgumentException("Unknown template source: $templateSource")
        }
    }

    override fun closeTemplateSource(templateSource: Any) {
        // No explicit close required as stream is closed after reading
    }

    private fun extractSiteFromTemplateName(name: String): String {
        return name.substringBefore("/", "templates/shared")
    }
}
