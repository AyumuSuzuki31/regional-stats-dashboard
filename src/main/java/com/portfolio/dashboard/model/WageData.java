package com.portfolio.dashboard.model;

public class WageData {
  private String prefectureCode;
  private String prefectureName;
  private String year;
  private Long averageWage;

  public WageData() {}

  public WageData(String prefectureCode, String prefectureName, String year, Long averageWage) {
    this.prefectureCode = prefectureCode;
    this.prefectureName = prefectureName;
    this.year = year;
    this.averageWage = averageWage;
  }

  public String getPrefectureCode() {
    return prefectureCode;
  }

  public void setPrefectureCode(String prefectureCode) {
    this.prefectureCode = prefectureCode;
  }

  public String getPrefectureName() {
    return prefectureName;
  }

  public void setPrefectureName(String prefectureName) {
    this.prefectureName = prefectureName;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public Long getAverageWage() {
    return averageWage;
  }

  public void setAverageWage(Long averageWage) {
    this.averageWage = averageWage;
  }

  @Override
  public String toString() {
    return "WageData{" +
        "prefectureCode='" + prefectureCode + '\'' +
        ", prefectureName='" + prefectureName + '\'' +
        ", year='" + year + '\'' +
        ", averageWage=" + averageWage +
        '}';
  }
}

/*
=== WageDataクラスの詳細解説 ===

【クラスの役割】
都道府県別の賃金（年収）データを格納するデータ転送オブジェクト（DTO）

【フィールド説明】
1. prefectureCode (String)
   - 都道府県コード（01-47）
   - APIリクエストやデータベース検索のキー

2. prefectureName (String)
   - 都道府県名（東京都、大阪府など）
   - UI表示用の人間が読みやすい名前

3. year (String)
   - データの対象年（2020、2021など）
   - 時系列データの管理用

4. averageWage (Long)
   - 平均年収（円単位）
   - 賃金の実数値、Long型で大きな数値に対応

【設計のポイント】
- PopulationDataと同じ構造で一貫性確保
- JSON変換時の自動マッピングに対応
- 後からフィールド追加が容易な拡張可能設計

【使用場面】
- REST APIのレスポンス形式
- サービス層でのデータ処理
- フロントエンドでのJavaScript連携
*/