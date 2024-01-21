package com.internshala.indiexhackathon

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.net.Uri
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnStartVoiceRecognition: Button
    private lateinit var textViewChat: TextView
    private lateinit var speechRecognizer: SpeechRecognizer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStartVoiceRecognition = findViewById(R.id.btnStartVoiceRecognition)
        textViewChat = findViewById(R.id.textViewChat)

        btnStartVoiceRecognition.setOnClickListener {
            startVoiceRecognition()
            startButtonAnimation() // Start the rotation animation
        }

        setupSpeechRecognizer()
    }

    private fun startButtonAnimation() {
        // Rotate the button 360 degrees on the Y-axis
        val rotationAnimator = ObjectAnimator.ofFloat(btnStartVoiceRecognition, View.ROTATION_Y, 0f, 360f)
        rotationAnimator.duration = 1000 // Animation duration in milliseconds
        rotationAnimator.start()
    }

    private fun startVoiceRecognition() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")

        try {
            speechRecognizer.startListening(intent)
        } catch (e: Exception) {
            // Handle exception
            e.printStackTrace()
        }
    }

    private fun setupSpeechRecognizer() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)

        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {}
            override fun onBeginningOfSpeech() {}
            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onEndOfSpeech() {}
            override fun onError(error: Int) {}
            override fun onResults(results: Bundle?) {
                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                if (!matches.isNullOrEmpty()) {
                    val spokenText = matches[0]
                    checkPronunciation(spokenText)
                }
            }

            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })
    }

    private fun checkPronunciation(spokenText: String) {
        val pronunciationDictionary = mapOf(
            "example" to "example",
            "apple" to "apple",
            "banana" to "banana",
            "elephant" to "elephant",
            "giraffe" to "giraffe",
            "chocolate" to "chocolate",
            "algorithm" to "algorithm",
            "happiness" to "happiness",
            "technology" to "technology",
            "communication" to "communication",
            "abandon" to "abandon",
            "ability" to "ability",
            "absolutely" to "absolutely",
            "accumulate" to "accumulate",
            "acknowledge" to "acknowledge",
            "adventure" to "adventure",
            "advocate" to "advocate",
            "affection" to "affection",
            "aggregate" to "aggregate",
            "alleviate" to "alleviate",
            "beautiful" to "beautiful",
            "benevolent" to "benevolent",
            "caterpillar" to "caterpillar",
            "celestial" to "celestial",
            "challenge" to "challenge",
            "compassion" to "compassion",
            "delicate" to "delicate",
            "effervescent" to "effervescent",
            "eloquent" to "eloquent",
            "fantastic" to "fantastic",
            "gratitude" to "gratitude",
            "harmony" to "harmony",
            "illuminate" to "illuminate",
            "imagination" to "imagination",
            "infinite" to "infinite",
            "jubilant" to "jubilant",
            "kaleidoscope" to "kaleidoscope",
            "luminous" to "luminous",
            "magnificent" to "magnificent",
            "nurturing" to "nurturing",
            "optimistic" to "optimistic",
            "paradise" to "paradise",
            "quintessential" to "quintessential",
            "resplendent" to "resplendent",
            "serendipity" to "serendipity",
            "tranquil" to "tranquil",
            "umbrella" to "umbrella",
            "vibrant" to "vibrant",
            "wonderful" to "wonderful",
            "xylophone" to "xylophone",
            "yesterday" to "yesterday",
            "zeppelin" to "zeppelin",
            "ambitious" to "ambitious",
            "bewilder" to "bewilder",
            "captivate" to "captivate",
            "dexterity" to "dexterity",
            "effulgent" to "effulgent",
            "felicitous" to "felicitous",
            "gargantuan" to "gargantuan",
            "higgledy-piggledy" to "higgledy-piggledy",
            "incandescent" to "incandescent",
            "juxtapose" to "juxtapose",
            "kaleidoscopic" to "kaleidoscopic",
            "labyrinthine" to "labyrinthine",
            "mellifluous" to "mellifluous",
            "nostalgia" to "nostalgia",
            "oblivion" to "oblivion",
            "peregrination" to "peregrination",
            "quizzical" to "quizzical",
            "recumbentibus" to "recumbentibus",
            "serendipitous" to "serendipitous",
            "tintinnabulation" to "tintinnabulation",
            "ubiquitous" to "ubiquitous",
            "verisimilitude" to "verisimilitude",
            "wherewithal" to "wherewithal",
            "xenodochial" to "xenodochial",
            "yonderly" to "yonderly",
            "zeitgeist" to "zeitgeist",
            // Additional Common Words
            "hello" to "hello",
            "goodbye" to "goodbye",
            "please" to "please",
            "thank you" to "thank you",
            "sorry" to "sorry",
            "yes" to "yes",
            "no" to "no",
            "excuse me" to "excuse me",
            "sorry" to "sorry",
            "pardon" to "pardon",
            "ok" to "ok",
            "fine" to "fine",
            "help" to "help",
            "thanks" to "thanks",
            "bye" to "bye",
            "nice" to "nice",
            "love" to "love",
            "friend" to "friend",
            "family" to "family",
            "home" to "home",
            "work" to "work",
            "play" to "play",
            "eat" to "eat",
            "drink" to "drink",
            "sleep" to "sleep",
            "happy" to "happy",
            "sad" to "sad",
            "angry" to "angry",
            "funny" to "funny",
            "serious" to "serious",
            "crazy" to "crazy",
            "cool" to "cool",
            "hot" to "hot",
            "cold" to "cold",
            "fast" to "fast",
            "slow" to "slow",
            "big" to "big",
            "small" to "small",
            "old" to "old",
            "new" to "new",
            "high" to "high",
            "low" to "low",
            "rich" to "rich",
            "poor" to "poor",
            "happy" to "happy",
            "sad" to "sad",
            "angry" to "angry",
            "love" to "love",
            "hate" to "hate",
            "beautiful" to "beautiful",
            "ugly" to "ugly",
            "clean" to "clean",
            "dirty" to "dirty",
            "easy" to "easy",
            "difficult" to "difficult",
            "cheap" to "cheap",
            "expensive" to "expensive",
            "old" to "old",
            "young" to "young",
            "long" to "long",
            "short" to "short",
            "near" to "near",
            "far" to "far",
            "right" to "right",
            "left" to "left",
            "good" to "good",
            "bad" to "bad",
            "happy" to "happy",
            "sad" to "sad",
            "angry" to "angry",
            "love" to "love",
            "hate" to "hate",
            "fast" to "fast",
            "slow" to "slow",
            "hot" to "hot",
            "cold" to "cold",
            "strong" to "strong",
            "weak" to "weak",
            "thick" to "thick",
            "thin" to "thin",
            "deep" to "deep",
            "shallow" to "shallow",
            "dark" to "dark",
            "light" to "light",
            "noisy" to "noisy",
            "quiet" to "quiet",
            "empty" to "empty",
            "full" to "full",
            "hard" to "hard",
            "soft" to "soft",
            "clean" to "clean",
            "dirty" to "dirty",
            "wet" to "wet",
            "dry" to "dry",
            "smooth" to "smooth",
            "rough" to "rough",
            "sweet" to "sweet",
            "sour" to "sour",
            "bitter" to "bitter",
            "spicy" to "spicy",
            "tasty" to "tasty",
            "delicious" to "delicious",
            "fresh" to "fresh",
            "stale" to "stale",
            "healthy" to "healthy",
            "unhealthy" to "unhealthy",
            "happy" to "happy",
            "sad" to "sad",
            "angry" to "angry",
            "love" to "love",
            "hate" to "hate",
            "alive" to "alive",
            "dead" to "dead",
            "right" to "right",
            "wrong" to "wrong",
            "true" to "true",
            "false" to "false",
            "open" to "open",
            "closed" to "closed",
            "begin" to "begin",
            "end" to "end",
            "start" to "start",
            "stop" to "stop",
            "win" to "win",
            "lose" to "lose",
            "pass" to "pass",
            "fail" to "fail",
            "create" to "create",
            "destroy" to "destroy",
            "build" to "build",
            "break" to "break",
            "buy" to "buy",
            "sell" to "sell",
            "find" to "find",
            "lose" to "lose",
            "give" to "give",
            "take" to "take",
            "enter" to "enter",
            "exit" to "exit",
            "win" to "win",
            "lose" to "lose",
            "help" to "help",
            "hurt" to "hurt",
            "arrive" to "arrive",
            "leave" to "leave",
            "remember" to "remember",
            "forget" to "forget",
            "speak" to "speak",
            "listen" to "listen",
            "read" to "read",
            "write" to "write",
            "learn" to "learn",
            "teach" to "teach",
            "understand" to "understand",
            "confuse" to "confuse",
            "win" to "win",
            "lose" to "lose",
            "wait" to "wait",
            "hurry" to "hurry",
            "begin" to "begin",
            "end" to "end",
            "start" to "start",
            "stop" to "stop",
            "like" to "like",
            "dislike" to "dislike",
            "enjoy" to "enjoy",
            "suffer" to "suffer",
            "laugh" to "laugh",
            "cry" to "cry",
            "smile" to "smile",
            "frown" to "frown",
            "live" to "live",
            "die" to "die",
            "hope" to "hope",
            "fear" to "fear",
            "dream" to "dream",
            "wake up" to "wake up",
            "sleep" to "sleep",
            "work" to "work",
            "rest" to "rest",
            "play" to "play",
            "study" to "study",
            "travel" to "travel",
            "stay" to "stay",
            "move" to "move",
            "talk" to "talk",
            "silent" to "silent",
            "see" to "see",
            "blind" to "blind",
            "thank you" to "thank you",
            "understand" to "understand",
            "happy birthday" to "happy birthday",
            "congratulations" to "congratulations",
            "good morning" to "good morning",
            "good afternoon" to "good afternoon",
            "good evening" to "good evening",
            "good night" to "good night",
            "how are you?" to "how are you?",
            "I'm fine, thank you" to "I'm fine, thank you",
            "what's your name?" to "what's your name?",
            "I love coding." to "I love coding.",
            "The sun is shining." to "The sun is shining.",
            "I enjoy reading books." to "I enjoy reading books.",
            "Life is beautiful." to "Life is beautiful.",
            "Let's go for a walk." to "Let's go for a walk.",
            "I'm feeling great today." to "I'm feeling great today.",
            "It's a lovely day." to "It's a lovely day.",
            "Can you help me, please?" to "Can you help me, please?",
            "What's for dinner?" to "What's for dinner?",
            // Add more words and their correct pronunciations here
        )

        val bestMatch = findBestMatch(spokenText, pronunciationDictionary)

        if (bestMatch != null) {
            updateChat("Bot: Pronunciation of '${bestMatch.first}' is correct!")
            playSparkleAnimation()
        } else {
            updateChat("Bot: Pronunciation needs improvement. Try again!")
        }
    }

    private fun findBestMatch(spokenText: String, pronunciationDictionary: Map<String, String>): Pair<String, Double>? {
        var bestMatch: Pair<String, Double>? = null

        for ((word, correctPronunciation) in pronunciationDictionary) {
            val similarity = calculateSimilarity(spokenText, correctPronunciation)
            println("Word: $word, Similarity: $similarity")

            if (bestMatch == null || similarity > bestMatch.second) {
                bestMatch = Pair(word, similarity)
            }
        }

        // Adjust the threshold based on your requirements
        return if (bestMatch?.second ?: 0.0 >= 0.7) bestMatch else null
    }

    private fun playSparkleAnimation() {
        val sparkleAnimation = AnimationUtils.loadAnimation(this, R.anim.sparkle_animation)
        textViewChat.startAnimation(sparkleAnimation)
    }

    private fun calculateSimilarity(text1: String, text2: String): Double {
        val maxLength = maxOf(text1.length, text2.length)
        val editDistance = LevenshteinDistance.calculate(text1, text2)

        // Use a similarity ratio based on the length of the strings
        val similarity = 1.0 - (editDistance.toDouble() / maxLength.toDouble())

        return similarity
    }

    object LevenshteinDistance {
        fun calculate(s1: String, s2: String): Int {
            val m = s1.length
            val n = s2.length
            val d = Array(m + 1) { IntArray(n + 1) }

            for (i in 0..m) {
                for (j in 0..n) {
                    if (i == 0) {
                        d[i][j] = j
                    } else if (j == 0) {
                        d[i][j] = i
                    } else {
                        d[i][j] = minOf(
                            d[i - 1][j] + 1,
                            d[i][j - 1] + 1,
                            d[i - 1][j - 1] + if (s1[i - 1] == s2[j - 1]) 0 else 1
                        )
                    }
                }
            }
            return d[m][n]
        }
    }


    private fun updateChat(message: String) {
        textViewChat.text = message
    }

    // Override onDestroy to release SpeechRecognizer
    override fun onDestroy() {
        super.onDestroy()
        speechRecognizer.destroy()
    }

    fun openLink(view: View) {
        // Specify the URL you want to open
        val url = "https://www.duolingo.com/"

        // Create an Intent with the ACTION_VIEW action and the URL
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

        // Start the activity with the intent
        startActivity(intent)
    }
}

