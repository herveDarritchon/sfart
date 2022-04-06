package sfart.semanticfield.hervedarritchon.fr.infrastructure.scrapper.RimesSolides

import it.skrape.core.document
import it.skrape.fetcher.BrowserFetcher
import it.skrape.fetcher.response
import it.skrape.fetcher.skrape
import it.skrape.selects.html5.div
import it.skrape.selects.html5.h1
import org.slf4j.LoggerFactory

class RimesSolidesScrapperEngine {
    companion object {
        private val logger = LoggerFactory.getLogger(RimesSolidesScrapperEngine::class.java)
    }
    fun extract(word: String, depth: Int): RimesSolidesResponse {
        logger.debug("parameters : word=$word / depth=$depth")
        val result = skrape(BrowserFetcher) { // <--- pass BrowserFetcher to include rendered JS
            request { url = "https://www.rimessolides.com/motscles.aspx?m=${word}" }
            response { this }
        }

        val extracted = with(result) {
            val keyTag = document.div { findFirst("#divResultat") }
            val title = keyTag
                .div {
                    withClass = "entete"
                    findFirst {
                        h1 {
                            findFirst {
                                text
                            }
                        }
                    }
                }
            val keywords = keyTag.children { filter { it.hasClass("motcle") } }.map { it.text }
            RimesSolidesResponse(
                httpStatusCode = status { code },
                httpStatusMessage = status { message },
                key = title.split("\"")[1],
                keywords = keywords.filter { it[0].isLowerCase() },
                depth = depth,
            )
        }

        return extracted
        // will print:
        // MyDataClass(httpStatusCode=200, httpStatusMessage=OK, paragraph=i'm a paragraph, allParagraphs=[i'm a paragraph, i'm a second paragraph], allLinks=[http://some.url, http://some-other.url])
    }

}