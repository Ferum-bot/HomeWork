# How api serialization works.

There is a class `DAGUtils` in the library which allows serializing and deserializing the DAG state.

* Use `exportAsString(Space)` to serialize
* Use `importFromString(String)` to deserialize

[Click]() for class docs.

## How does it works?
Well, serialization takes place in a highly simplified object in the likeness of JSON.

Every entity has each types and coordinates. And some of them has children. 

### Point example:
```
{
    type: POINT
    coordinates: (0.0, 0.0)
}
```
### Origin example:

```
{
    type: ORIGIN
    coordinates: (1.1, 2.2)
    children:
    [
        {
            type: POINT
            coordinates: (0.0, 0.0)
        }
    ]
}
```

### Space example:
```
{
    type: SPACE
    coordinates: (0.0, 0.0)
    children:
    [
        {
            type: POINT
            coordinates: (1.1, 0)
        }
        {
            type: POINT
            coordinates: (12.2, 123.2)
        }
        {
            type: ORIGIN
            coordinates: (-12.2, 3)
            children:
            [
                {
                    type: POINT
                    coordinates: (54.123, -0.2)
                }
            ]
        }
    ]
}
```