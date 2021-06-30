# An issue in OkHttp Kotlin

## build

Use IDEA to run the `Application.kt` file.

## Description

### Dependency Graph

```
    OkHttp -> Framework -> Project
```

In this case, we can assume that framework prepares some commonly used OkHttp operations for every module in the whole project.

### What Happened

#### `framework` module

In module `framework`, we can run all the OkHttp operations successfully.

But we can't compile `MediaType.parse("plain/text; charset=utf-8")` in the main function, because the code of OkHttp blocked it.

```
    @JvmName("-deprecated_parse")
    @Deprecated(
        message = "moved to extension function",
        replaceWith = ReplaceWith(
            expression = "mediaType.toMediaTypeOrNull()",
            imports = ["okhttp3.MediaType.Companion.toMediaTypeOrNull"]),
        level = DeprecationLevel.ERROR)     // <------- this line
    fun parse(mediaType: String): MediaType? = mediaType.toMediaTypeOrNull()
```

#### `project` module

In module `project`, we can only run the OkHttp operation which is written by java in `framework` successfully.

But we can compile `MediaType.parse("plain/text; charset=utf-8")` in the main function.

## Something I guess

In `framework` module, `@Deprecated` blocks this kind of usage, but `@JvmStatic @JvmName` let the function below visible to `project`.

```
    @JvmStatic
    @JvmName("parse")
    fun String.toMediaTypeOrNull(): MediaType? {
      return try {
        toMediaType()
      } catch (_: IllegalArgumentException) {
        null
      }
    }
```

We can use `MediaType.parse("plain/text; charset=utf-8")` in `project` module.

Because of the same reason. `@JvmStatic @JvmName` let `okhttp3.MediaType.Companion.toMediaTypeOrNull` become `okhttp3.MediaType.parse`.

We can see an `Exception in thread "main" java.lang.NoSuchFieldError: Companion` error when running main function of `project` module.

## Conclusion

I am not sure what this issue blame to, but I prefer to change the `DepreciationLevel` to a lower level to resolve this issue temporary.