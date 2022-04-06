package sfart.semanticfield.hervedarritchon.fr.infrastructure.scrapper.robert

import it.skrape.core.document
import it.skrape.core.htmlDocument
import it.skrape.fetcher.BrowserFetcher
import it.skrape.fetcher.extractIt
import it.skrape.fetcher.skrape
import it.skrape.selects.eachText
import it.skrape.selects.html5.div
import it.skrape.selects.html5.h3
import it.skrape.selects.html5.span
import org.slf4j.LoggerFactory
import sfart.semanticfield.hervedarritchon.fr.infrastructure.scrapper.RimesSolides.RimesSolidesScrapperEngine

class RobertScrapperEngine {

    companion object {
        private val logger = LoggerFactory.getLogger(RimesSolidesScrapperEngine::class.java)
    }

    fun extract(word: String, depth: Int): RobertResponse {
        val result = skrape(BrowserFetcher) { // <--- pass BrowserFetcher to include rendered JS
            request { url = "https://docs.skrape.it/docs/" }
            extractIt<RobertResponse> {
                logger.debug("sfart-document:${document}")
                logger.debug("sfart-body:${responseBody}")
                logger.debug("sfart-divs:${htmlDocument {  { findAll { eachText } } }}")
                val keyTag = document.div {
                    findAll {
                        get(0)
                    }
                }
                val title = keyTag
                    .div {
                        withClass = "b"
                        h3 {
                            this.findFirst("")
                        }
                    }
                val keywords = title
                    .span {
                        this.findFirst("d_cat")
                    }
                    .text
                    .split(" ")

                it.httpStatusCode = status { code }
                it.httpStatusMessage = status { message }
                it.depth = depth
                htmlDocument {
                    it.key = title.text
                    it.gender = keywords[0]
                    it.nature = keywords[1]
                }
            }
        }

        return result
        // will print:
        // MyDataClass(httpStatusCode=200, httpStatusMessage=OK, paragraph=i'm a paragraph, allParagraphs=[i'm a paragraph, i'm a second paragraph], allLinks=[http://some.url, http://some-other.url])
    }

}