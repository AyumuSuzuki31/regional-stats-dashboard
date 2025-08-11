package com.portfolio.dashboard.model;

public class PopulationData {
  private String prefectureCode;
  private String prefectureName;  // ← ここを小文字に修正
  private String year;
  private Long population;

  public PopulationData() {}

  public PopulationData(String prefectureCode, String prefectureName, String year, Long population) {
    this.prefectureCode = prefectureCode;
    this.prefectureName = prefectureName;  // ← ここも修正
    this.year = year;
    this.population = population;
  }

  public String getPrefectureCode() {
    return prefectureCode;
  }

  public void setPrefectureCode(String prefectureCode) {
    this.prefectureCode = prefectureCode;
  }

  public String getPrefectureName() {
    return prefectureName;  // ← ここも修正
  }

  public void setPrefectureName(String prefectureName) {
    this.prefectureName = prefectureName;  // ← ここも修正
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public Long getPopulation() {
    return population;
  }

  public void setPopulation(Long population) {
    this.population = population;
  }
}

/*
========================================
PopulationData クラス解説
========================================

【クラス全体の役割】
- 人口データを表現するデータモデルクラス（DTO: Data Transfer Object）
- アプリケーション内でのデータの受け渡しに使用
- e-stat APIから取得したデータや、フロントエンドとの間でやり取りするデータの構造を定義
- Spring MVCアーキテクチャのModel層を担当

【DTOパターンとは】
- Data Transfer Object = データを運ぶためのオブジェクト
- ビジネスロジックは持たず、データの保持のみが目的
- 異なる層（Controller、Service、外部API）間でのデータ交換に使用
- データの構造を統一し、型安全性を確保

【フィールドの説明】
private String prefectureCode:
- 都道府県コード（例："13"=東京都、"27"=大阪府）
- String型で保持（数値だが先頭ゼロ付きのため文字列が適切）
- e-stat APIのリクエスト時に使用する識別子

private String prefectureName:
- 都道府県名（例："東京都"、"大阪府"）
- 表示用の人間が読みやすい名前
- UIでグラフの凡例やラベルとして使用

private String year:
- 統計年（例："2015"、"2020"）
- 統計データの対象年度
- グラフのX軸ラベルとして使用

private Long population:
- 人口数（例：1000000）
- Long型を使用（大きな数値に対応、nullの可能性もあるため）
- グラフのY軸データとして使用

【アクセス修飾子 private の意味】
- フィールドを外部から直接アクセスできないようにする
- データの整合性を保つためのカプセル化
- 必ずgetter/setterメソッド経由でアクセスすることを強制

【コンストラクタの説明】
public PopulationData():
- デフォルトコンストラクタ（引数なし）
- Springのデータバインディングで必要
- JSON⇔Javaオブジェクト変換時にフレームワークが使用

public PopulationData(String prefectureCode, ...):
- 全フィールドを初期化するコンストラクタ
- オブジェクト作成時に一度に全ての値を設定可能
- 引数の順序は重要（呼び出し側と一致させる必要）

【getter/setterメソッドの説明】
Javaのカプセル化の慣例に従ったアクセサメソッド

getter（取得メソッド）:
- public String getPrefectureCode(): prefectureCodeの値を取得
- 命名規則：get + フィールド名（先頭大文字）
- 戻り値の型はフィールドと同じ
- フィールドの値をそのまま返却

setter（設定メソッド）:
- public void setPrefectureCode(String prefectureCode): prefectureCodeに値を設定
- 命名規則：set + フィールド名（先頭大文字）
- 戻り値はvoid（何も返さない）
- this.fieldName = parameterName でフィールドに値を代入

【thisキーワードの説明】
this.prefectureName = prefectureName:
- this = 現在のオブジェクト自身を指す
- 左側：オブジェクトのフィールド
- 右側：メソッドの引数
- 名前が同じ場合にthisで区別する

【JSON変換での動作】
SpringBootの@RestControllerでこのオブジェクトを返すと：
1. Jackson（JSON変換ライブラリ）が自動的に作動
2. 各getterメソッドを呼び出してフィールド値を取得
3. メソッド名から"get"を除いた部分がJSONのキー名になる
4. getPrefectureName() → "prefectureName"

【命名規則の重要性】
- prefectureName（正）vs PrefectureName（誤）
- Javaの慣例：フィールド/メソッド名は小文字開始のキャメルケース
- JSON出力時のキー名に直結するため、一貫性が重要
- フロントエンド（JavaScript）との連携時にも影響

【データの流れ】
1. e-stat API → JSON形式でデータ受信
2. PopulationDataオブジェクトに変換（Jacksonが自動実行）
3. Service層でビジネスロジック処理
4. Controller層でJSONとしてフロントエンドに送信
5. JavaScript（Chart.js）でグラフ描画に使用
*/