# MyKraken

This is a simple API to access the Kraken bitcoin exchange API.

It currently supports the following public calls:

- getTicker(pairs: String) : Observable\<TickerResult\>
- getOHLC(pair: String, interval: Int, sinceId: Int = 0): Observable\<OHLCResult\>
- getSpread(pair: String, sinceId: Int = 0): Observable\<SpreadResult\>
- getAssets(): Observable\<AssetsResult\>
- getTradableAssetPairs(): Observable\<AssetPairsResult\>
- getServerTime(): Observable\<ServerTimeResult\>

All calls use RX and return typed objects in Observables.

For example to get the current ticker for Litecoin in €:

val krakenApi:KrakenApi = KrakenApi()
krakenApi().service.getTicker("LTCEUR").subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe(
        { result: TickerResult -> showResult(result) },
        { error -> showError(error) }
    )
