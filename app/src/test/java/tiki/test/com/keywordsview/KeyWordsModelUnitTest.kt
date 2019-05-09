package tiki.test.com.keywordsview

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.spy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(JUnit4::class)
class KeyWordsModelUnitTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    @Mock
    lateinit var text: String

    lateinit var keywordsModel: KeywordsModel

    @Before
    fun setUp() {
        keywordsModel = spy(KeywordsModel())
    }

    @Test
    fun generateTextData_positiveResponse() {
        assertNotNull(keywordsModel)
        assertEquals( "nguyễn"+'\n'+"nhật ánh",keywordsModel.generateTextData("nguyễn nhật ánh"))
        assertEquals( "anh chính là"+'\n'+"thanh xuân của em",keywordsModel.generateTextData("anh chính là thanh xuân của em"))
        assertNotEquals( "tai nghe bluetooth",keywordsModel.generateTextData("tai nghe bluetooth"))
    }
}
