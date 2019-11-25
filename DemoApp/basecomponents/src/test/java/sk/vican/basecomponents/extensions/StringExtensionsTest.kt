package sk.vican.basecomponents.extensions

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class StringExtensionsTest {

    @Test
    fun whenStringConvertToSHA1String_thenConvertionReturnRightSHA1String(){
        // given
        val sha1String = "1dee4b1affb249bc74c20379e60166366b71394f"
        val plainString = "testsha1"

        // when
        val sha1Converted = plainString.stringToSHA1()

        // then
        assertThat(sha1Converted, `is`(sha1String))

    }

}
