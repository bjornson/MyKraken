# MyKraken - Kraken Rest Api for Android

This is a simple API to access the Kraken bitcoin exchange API. (see https://www.kraken.com/help/api)

### It currently supports the following public calls:

```
getTicker(pairs: String) : Observable<TickerResult>
getOHLC(pair: String, interval: Int, sinceId: Int = 0): Observable<OHLCResult>
getSpread(pair: String, sinceId: Int = 0): Observable<SpreadResult>
getAssets(): Observable<AssetsResult>
getTradableAssetPairs(): Observable<AssetPairsResult>
getServerTime(): Observable<ServerTimeResult>
```
All calls use RX and return typed objects in Observables.

For example to get the current ticker for Litecoin in €:

```
val krakenApi:KrakenApi = KrakenApi()
krakenApi().service.getTicker("LTCEUR").subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe(
        { result: TickerResult -> showResult(result) },
        { error -> showError(error) }
    )
```
## Include it in your android project

You can pull the whole repository or let gradle do it for you with the following steps:

### Include jitpack in your project repositories, in your root gradle add:
```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

### Implement the library in your app module, add in your app gradle:
```
dependencies {
  // MyKraken
  implementation 'com.github.bjornson:MyKraken:0.1.0'
  // RX android
  implementation "io.reactivex.rxjava2:rxandroid:2.0.1"
  // GSON
  implementation 'com.google.code.gson:gson:2.8.5'
}
```
