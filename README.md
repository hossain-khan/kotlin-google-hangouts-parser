# Google Hangouts JSON Parser
Contains JSON model classes using [Moshi](https://github.com/square/moshi) JSON parser. See [JSON model classes](https://github.com/amardeshbd/kotlin-google-hangouts-parser/tree/master/library/src/main/kotlin/dev/hossain/hangouts/model) sources. 

The Google Hangouts JSON file can be optained via Google Takeout at https://takeout.google.com/settings/takeout

See example below:  
<img width="80%" alt="Google Takeout - Hangouts" src="https://user-images.githubusercontent.com/99822/85938177-c70a2900-b8d8-11ea-9917-8d57b32b045f.png">


## Usage

I am still experimenting with the takeout JSON data. However, here is how to use (use [JitPack.io](https://jitpack.io/#amardeshbd/kotlin-google-hangouts-parser) to add gradle dependency)


```kotlin
// import dev.hossain.hangouts.Parser

    val file = File("/path/to/hangouts.json")

    val source = Okio.buffer(Okio.source(file))
    
    val hangoutsDocument: HangoutsDocument = Parser.parse(source)
    println("Completed processing - got ${hangoutsDocument.conversations.size} conversations.")

```

### Data Snapshot
Here is a data snapshot taken from IntelliJ IDEA debugger.  
![](https://user-images.githubusercontent.com/99822/85952328-7a553b00-b936-11ea-80ca-4bb502382425.png)
