![Kotlin-hangouts-parser](https://user-images.githubusercontent.com/99822/86419839-8a587c00-bca2-11ea-9bb2-3406bf5c2601.jpg)

# Google Hangouts JSON Parser
Contains JSON model classes using [Moshi](https://github.com/square/moshi) JSON parser. See [JSON model classes](https://github.com/amardeshbd/kotlin-google-hangouts-parser/tree/master/library/src/main/kotlin/dev/hossain/hangouts/model) sources. 

The Google Hangouts JSON file can be optained via Google Takeout at https://takeout.google.com/settings/takeout

See example below:  
<img width="80%" alt="Google Takeout - Hangouts" src="https://user-images.githubusercontent.com/99822/85938177-c70a2900-b8d8-11ea-9917-8d57b32b045f.png">


## Usage

I am still experimenting with the takeout JSON data. However, here is how to use the library to parse the `Hangouts.json` file (use [JitPack.io](https://jitpack.io/#amardeshbd/kotlin-google-hangouts-parser) to add gradle dependency)


```kotlin
// import dev.hossain.hangouts.Parser (also see `HangoutsJsonProcessor.kt` for sample usage)

    val file = File("/path/to/hangouts.json")

    val source: BufferedSource = Okio.buffer(Okio.source(file)) // Use buffer to read large file
    
    val hangoutsDocument: HangoutsDocument = Parser.parse(source)
    println("Completed processing - got ${hangoutsDocument.conversations.size} conversations.")

```

### Data Snapshot
Here is a data snapshot taken from IntelliJ IDEA debugger.  

![](https://user-images.githubusercontent.com/99822/85952328-7a553b00-b936-11ea-80ca-4bb502382425.png)


### SQLite Database
The example project creates a SQLite database using SQLDelight found in [`data`](https://github.com/amardeshbd/kotlin-google-hangouts-parser/tree/master/data/src/main/sqldelight/hangouts/data) module. 
See [HangoutsJsonProcessor.kt](https://github.com/amardeshbd/kotlin-google-hangouts-parser/blob/master/example/src/main/kotlin/dev/hossain/hangouts/example/HangoutsJsonProcessor.kt) for details.

Here is sample snapshot of output from the `HangoutsJsonProcessor.kt`.

```
┌────────────────┬─────────────────────────┬──────────┐
│                │                  Total  │   417    │
│                ├─────────────────────────┼──────────┤
│  Conversation  │     Group Conversation  │    41    │
│                ├─────────────────────────┼──────────┤
│                │      One-to-one Thread  │   376    │
├────────────────┼─────────────────────────┼──────────┤
│                │                  Total  │   435    │
│                ├─────────────────────────┼──────────┤
│  Participants  │           Google Users  │   112    │
│                ├─────────────────────────┼──────────┤
│                │       Non-Google (SMS)  │   323    │
├────────────────┼─────────────────────────┼──────────┤
│                │                  Total  │  176932  │
│                ├─────────────────────────┼──────────┤
│                │          Text Messages  │  168733  │
│  Chat Message  ├─────────────────────────┼──────────┤
│                │       Web URL Messages  │   3741   │
│                ├─────────────────────────┼──────────┤
│                │  Longest Message Chars  │   1446   │
└────────────────┴─────────────────────────┴──────────┘
```
