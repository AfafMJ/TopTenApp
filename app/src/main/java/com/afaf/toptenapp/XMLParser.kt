package com.afaf.toptenapp


import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.net.URL

class XMLParser {
    private var text: String? = null

    private lateinit var apps: ArrayList<App>
    private var appTitle = ""

    private var appCategories = ""


    fun pars(): ArrayList<App> {
        apps = arrayListOf()
        try {
            val factory = XmlPullParserFactory.newInstance()
            val parser = factory.newPullParser()
            val url =
                URL("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml")
            parser.setInput(url.openStream(), null)
            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = parser.name
                when (eventType) {
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> when {
                        tagName.equals("title", true) -> {
                            appTitle = text.toString()
                        }

                        tagName.equals("entry", true) -> {
                            appCategories = appCategories.removeSuffix(", ")
                            apps.add(App(appTitle))
                            appCategories = ""
                        }
                        else -> {}
                    }
                    else -> {}
                }
                eventType = parser.next()
            }
        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return apps
    }


}
