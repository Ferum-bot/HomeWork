# Mapper
Simple and lightweight java library saving and restoring Java objects by annotations. 
Library users will be able to save Java objects to a file, OutputStream, or String, 
and restore objects from a String, InputStream, or file.

## Supported features:
* Retain identity - mapper saves reference identity of objects.
* Thread safe - serialization/deserialization process contains only thread safe data.
* Record support - mapper supports records serialization and deserialization.
* File support - mapper can interact with files.
* Output and Input stream support - mapper can interact with Input/Output Stream.

[More information.](./Interface/src/main/java/ru/hse/homework4/Mapper.java)

## About annotations:

### Exported:
``@Exported`` annotation is main entity of Mapper library.
Only classes with this annotation can work with this library.

Supports two settings: ``nullHandling`` and ``unknownPropertiesPolicy``.
* NullHandling - defines null handling policy. [More information.](./Interface/src/main/java/ru/hse/homework4/Exported.java)
* UnknownPropertiesPolicy - defines unknown properties policy. [More information.](./Interface/src/main/java/ru/hse/homework4/Exported.java)

### Ignored:
``@Ignored`` annotation defines class properties which will be ignored during serialization.
[More information.](./Interface/src/main/java/ru/hse/homework4/Ignored.java)

### PropertyName:
``@PropertyName`` annotation defines class property name. [More information.](./Interface/src/main/java/ru/hse/homework4/PropertyName.java)


### DateFormat:
``@DateFormat`` annotation defines the format of java date classes during serialization and deserialization.
[More information](./Interface/src/main/java/ru/hse/homework4/DateFormat.java)

Supports java date/time classes:
* LocalDate
* LocalTime
* LocalDateTime

## How to use:

Add library dependency to your project ``pom.xml`` file. Example:
```xml
<dependencies>
        <dependency>
            <groupId>ru.hse.homework4</groupId>
            <artifactId>interface</artifactId>
            <version>1.0.0</version>
            <scope>compile</scope>
        </dependency>
    </dependencies>
```

Then create `Mapper` and start work! Example:
```java
public class WriteExample {
      public static void main(String[] args) throws IOException {
          ReviewComment reviewComment = new ReviewComment();
          reviewComment.setComment("Одни static'и в работе"); 
          reviewComment.setResolved(false); 
          reviewComment.setAuthor("Проверяющий #1");
          
          Mapper mapper = new DefaultMapper();
          String string = mapper.writeToString(reviewComment);
          System.out.println(string);
      }
}
```
```java
public class IdentityRetainingExample {
      public static void main(String[] args) throws IOException {
          Foo foo = new Foo();
          Bar bar = new Bar();
          foo.setBar0(bar);
          foo.setBar1(bar);
          
          boolean retainIdentity = true;
          Mapper mapper = new DefaultMapper(retainIdentity);
          Foo restored = mapper.readFromString(Foo.class, mapper.writeToString(foo)); 
          System.out.println(restored.getBar0() == restored.getBar1());
      }
}
```

## Popular issues:
* If project doesn't build due module cyclicity dependency try to do [this](https://stackoverflow.com/questions/27223917/how-to-configure-annotations-processing-in-intellij-idea-14-for-current-project). It might be help.

#### Created and powered by Matvey Popov.
#### Test coverage 72% 