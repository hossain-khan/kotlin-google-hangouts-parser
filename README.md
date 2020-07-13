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

![Google Sheets Generated Charts](https://user-images.githubusercontent.com/99822/86421294-80854780-bca7-11ea-969d-6980def9b3cd.png)


## JSON Data Reference

Here is a simlified snapshot of JSON dump with conversation and events containing chat message.

```json
{
  "conversations": [
    {
      "conversation": {
        "conversation_id": {
          "id": "cid-12345678"
        },
        "conversation": {
          "id": {
            "id": "cid-12345678"
          },
          "type": "GROUP",
          "self_conversation_state": {
            "self_read_state": {
              "participant_id": {
                "gaia_id": "users-google-user-id-123",
                "chat_id": "users-google-user-id-123"
              },
              "latest_read_timestamp": "1369968040768557"
            },
            "status": "ACTIVE",
            "notification_level": "RING",
            "view": [
              "ARCHIVED_VIEW"
            ],
            "inviter_id": {
              "gaia_id": "users-google-user-id-123",
              "chat_id": "users-google-user-id-123"
            },
            "invite_timestamp": "1369539123372000",
            "sort_timestamp": "1369968040768557",
            "active_timestamp": "1369539123372000",
            "delivery_medium_option": [
              {
                "delivery_medium": {
                  "medium_type": "BABEL_MEDIUM"
                },
                "current_default": true
              }
            ],
            "is_guest": false
          },
          "read_state": [
            {
              "participant_id": {
                "gaia_id": "users-google-user-id-567",
                "chat_id": "users-google-user-id-567"
              },
              "latest_read_timestamp": "0"
            },
            {
              "participant_id": {
                "gaia_id": "users-google-user-id-123",
                "chat_id": "users-google-user-id-123"
              },
              "latest_read_timestamp": "1369968040768557"
            },
            {
              "participant_id": {
                "gaia_id": "users-google-user-id-789",
                "chat_id": "users-google-user-id-789"
              },
              "latest_read_timestamp": "0"
            }
          ],
          "has_active_hangout": false,
          "otr_status": "ON_THE_RECORD",
          "otr_toggle": "ENABLED",
          "current_participant": [
            {
              "gaia_id": "users-google-user-id-567",
              "chat_id": "users-google-user-id-567"
            },
            {
              "gaia_id": "users-google-user-id-789",
              "chat_id": "users-google-user-id-789"
            },
            {
              "gaia_id": "users-google-user-id-123",
              "chat_id": "users-google-user-id-123"
            }
          ],
          "participant_data": [
            {
              "id": {
                "gaia_id": "users-google-user-id-567",
                "chat_id": "users-google-user-id-567"
              },
              "fallback_name": "yasir usama",
              "invitation_status": "ACCEPTED_INVITATION",
              "participant_type": "GAIA",
              "new_invitation_status": "ACCEPTED_INVITATION",
              "in_different_customer_as_requester": false,
              "domain_id": "domain-id-123"
            },
            {
              "id": {
                "gaia_id": "users-google-user-id-123",
                "chat_id": "users-google-user-id-123"
              },
              "fallback_name": "Hossain Khan",
              "invitation_status": "ACCEPTED_INVITATION",
              "participant_type": "GAIA",
              "new_invitation_status": "ACCEPTED_INVITATION",
              "in_different_customer_as_requester": false,
              "domain_id": "domain-id-123"
            },
            {
              "id": {
                "gaia_id": "users-google-user-id-789",
                "chat_id": "users-google-user-id-789"
              },
              "fallback_name": "Istiaque Ahmed",
              "invitation_status": "ACCEPTED_INVITATION",
              "participant_type": "GAIA",
              "new_invitation_status": "ACCEPTED_INVITATION",
              "in_different_customer_as_requester": false,
              "domain_id": "domain-id-123"
            }
          ],
          "fork_on_external_invite": false,
          "network_type": [
            "BABEL"
          ],
          "force_history_state": "NO_FORCE",
          "group_link_sharing_status": "LINK_SHARING_OFF"
        }
      },
      "events": [
        {
          "conversation_id": {
            "id": "cid-12345678"
          },
          "sender_id": {
            "gaia_id": "users-google-user-id-567",
            "chat_id": "users-google-user-id-567"
          },
          "timestamp": "1369539622561784",
          "self_event_state": {
            "user_id": {
              "gaia_id": "users-google-user-id-123",
              "chat_id": "users-google-user-id-123"
            },
            "notification_level": "RING"
          },
          "hangout_event": {
            "event_type": "END_HANGOUT",
            "participant_id": [
              {
                "gaia_id": "users-google-user-id-567",
                "chat_id": "users-google-user-id-567"
              },
              {
                "gaia_id": "users-google-user-id-123",
                "chat_id": "users-google-user-id-123"
              },
              {
                "gaia_id": "users-google-user-id-789",
                "chat_id": "users-google-user-id-789"
              }
            ],
            "hangout_duration_secs": "102"
          },
          "event_id": "7-H0Z7-Uqf17-H1W1MUbr7",
          "advances_sort_timestamp": true,
          "event_otr": "ON_THE_RECORD",
          "delivery_medium": {
            "medium_type": "BABEL_MEDIUM"
          },
          "event_type": "HANGOUT_EVENT",
          "event_version": "1369539622561784"
        },
        {
          "conversation_id": {
            "id": "cid-12345678"
          },
          "sender_id": {
            "gaia_id": "users-google-user-id-123",
            "chat_id": "users-google-user-id-123"
          },
          "timestamp": "1369548258628038",
          "self_event_state": {
            "user_id": {
              "gaia_id": "users-google-user-id-123",
              "chat_id": "users-google-user-id-123"
            },
            "client_generated_id": "1369548257038",
            "notification_level": "RING"
          },
          "chat_message": {
            "message_content": {
              "segment": [
                {
                  "type": "TEXT",
                  "text": "Chat message",
                  "formatting": {
                    "bold": false,
                    "italics": false,
                    "strikethrough": false,
                    "underline": false
                  }
                }
              ]
            }
          },
          "event_id": "7-H0Z7-Uqf17-HHzEcNqeT",
          "advances_sort_timestamp": true,
          "event_otr": "ON_THE_RECORD",
          "delivery_medium": {
            "medium_type": "BABEL_MEDIUM"
          },
          "event_type": "REGULAR_CHAT_MESSAGE",
          "event_version": "1369548258628038"
        }
      ]
    }
  ]
}
```
