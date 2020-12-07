package com.example.jsonlocal

import android.content.Context
import com.example.jsonlocal.ui.MainViewModel
import org.junit.After
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.runners.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

    private val ONE_WORD_MESSAGE = "ONE_WORD_MESSAGE"
    private val TWO_WORD_MESSAGE = "ONE WORD_MESSAGE"
    private val TWO_WORD_MESSAGE_RESULT = "WORD_MESSAGE ONE"
    private val TEN_WORD_MESSAGE = "WORD_MESSAGE ONE1 ONE2 ONE3 ONE4 ONE5 ONE6 ONE7 ONE8 ONE9"
    private val TEN_WORD_MESSAGE_RESULT = "ONE9 ONE8 ONE7 ONE6 ONE5 ONE4 ONE3 ONE2 ONE1 WORD_MESSAGE"

    private val SPACES_MESSAGE_RESULT = "     "
    private val SPACES_MESSAGE = "     "

    private lateinit var  vm : MainViewModel

    @Mock   // мокито тест
    private lateinit var context: Context


     @Before
     fun before(){
         vm = MainViewModel(context)
     }

    @Test   // для мокито
    fun loadString(){
        `when`(context.getString(R.string.app_name))
            .thenReturn("JsonLocal")
           val viewModel = MainViewModel(context)
        val result = viewModel.loadWord()
        assertEquals("JsonLocal",result)
    }

    @Test
    fun one_word_message_return_true(){
       val text = vm.replaceWords(ONE_WORD_MESSAGE)  // отправка
        assertEquals(ONE_WORD_MESSAGE,text)  // проверка
    }
    @Test
    fun two_word_message_return_true(){
        val text = vm.replaceWords(TWO_WORD_MESSAGE)  // отправка
        assertEquals(TWO_WORD_MESSAGE_RESULT,text)  // проверка
    }
    @Test
    fun tem_word_message_return_true(){
        val text = vm.replaceWords(SPACES_MESSAGE)  // отправка
        assertEquals(SPACES_MESSAGE_RESULT,text)  // проверка
    }

    @After
    fun after(){

    }
}