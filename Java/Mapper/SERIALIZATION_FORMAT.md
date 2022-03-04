# Serialization Format
Serialization takes place in a highly simplified object in the likeness of JSON.

There is also a small addition. All `@Exported` classes including records contains ID property `"$OBJECT_ID$"`
needed to retain identity serialization/deserialization.

## Examples
### Simple class:
```json
{
  "$OBJECT_ID$" : 0
  "name" : "name"
  "surname" : "surname"
  "count" : 124
  "last_price" : 123.3
  "currentDate" : "04.03.2022-20:40:46"
  "lastDate" : "04-03-2022"
}
```
### Retain identity class:
```json
{
  "$OBJECT_ID$" : 0
  "firstClass" : {
    "$OBJECT_ID$" : 1
    "name" : "name"
    "surname" : "surname"
    "count" : 124
    "last_price" : 123.3
    "currentDate" : "04.03.2022-20:42:36"
    "lastDate" : "04-03-2022"
  }
  "secondClass" : {
    "$OBJECT_ID$" : 1
    "name" : "name"
    "surname" : "surname"
    "count" : 124
    "last_price" : 123.3
    "currentDate" : "04.03.2022-20:42:36"
    "lastDate" : "04-03-2022"
  }
  "test1" : {
    "$OBJECT_ID$" : 8
    "notes" : [
      "TestNote1"
      "TestNote2"
      "TestNote3"
      "TestNote4"
    ]
    "costs" : [
      23.0
      -123.1
      0.123
      123.3
    ]
    "testEnum" : SECOND_VALUE
    "exportedTime" : "20:42:36"
    "nullString" : null
  }
  "test2" : {
    "$OBJECT_ID$" : 8
    "notes" : [
      "TestNote1"
      "TestNote2"
      "TestNote3"
      "TestNote4"
    ]
    "costs" : [
      23.0
      -123.1
      0.123
      123.3
    ]
    "testEnum" : SECOND_VALUE
    "exportedTime" : "20:42:36"
    "nullString" : null
  }
  "test3" : {
    "$OBJECT_ID$" : 8
    "notes" : [
      "TestNote1"
      "TestNote2"
      "TestNote3"
      "TestNote4"
    ]
    "costs" : [
      23.0
      -123.1
      0.123
      123.3
    ]
    "testEnum" : SECOND_VALUE
    "exportedTime" : "20:42:36"
    "nullString" : null
  }
}
```