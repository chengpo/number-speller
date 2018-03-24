/*
MIT License

Copyright (c) 2017 - 2018 Po Cheng

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package com.monkeyapp.numbers.translators

class NumberTranslator(private val speller: NumberSpeller) : Translator {
    private lateinit var updateNumber: (String, String) -> Unit
    private val composer = NumberComposer()

    override fun appendDigit(digit: Char) {
        if (composer.appendDigit(digit)) {
            notifyNumberUpdated()
        }
    }

    override fun deleteDigit() {
        if (composer.deleteDigit()) {
            notifyNumberUpdated()
        }
    }

    override fun resetDigit() {
        composer.resetDigit()
        notifyNumberUpdated()
    }

    override fun registerObserver(updateNumber: (digitStr:String, numberStr: String) -> Unit) {
        this.updateNumber = updateNumber
    }

    private fun notifyNumberUpdated() {
        if (composer.digitStr.isEmpty()) {
            updateNumber("","")
        } else {
            updateNumber(composer.digitStr,
                         speller.spellNumber(composer.integers, composer.decimals))
        }
    }
}