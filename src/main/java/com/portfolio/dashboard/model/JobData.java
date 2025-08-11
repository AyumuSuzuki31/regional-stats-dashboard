package com.portfolio.dashboard.model;

public class JobData {
  private String prefectureCode;
  private String prefectureName;
  private String year;
  private Double jobOfferRatio; // 有効求人倍率（倍）

  public JobData() {}

  public JobData(String prefectureCode, String prefectureName, String year, Double jobOfferRatio) {
    this.prefectureCode = prefectureCode;
    this.prefectureName = prefectureName;
    this.year = year;
    this.jobOfferRatio = jobOfferRatio;
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

  public Double getJobOfferRatio() {
    return jobOfferRatio;
  }

  public void setJobOfferRatio(Double jobOfferRatio) {
    this.jobOfferRatio = jobOfferRatio;
  }

  @Override
  public String toString() {
    return "JobData{" +
        "prefectureCode='" + prefectureCode + '\'' +
        ", prefectureName='" + prefectureName + '\'' +
        ", year='" + year + '\'' +
        ", jobOfferRatio=" + jobOfferRatio +
        '}';
  }
}

/*
=== JobDataクラスの詳細解説 ===

【クラスの役割】
都道府県別の有効求人倍率データを格納するデータ転送オブジェクト（DTO）

【有効求人倍率とは】
- 求職者1人当たりの求人数を表す経済指標
- 計算式：有効求人数 ÷ 有効求職者数
- 1.0を超えると求人が求職者を上回る（売り手市場）
- 1.0を下回ると求職者が求人を上回る（買い手市場）

【フィールド説明】
1. prefectureCode (String)
   - 都道府県コード（01-47）
   - 他のデータモデルとの統一性確保

2. prefectureName (String)
   - 都道府県名（東京都、大阪府など）
   - UI表示での可読性向上

3. year (String)
   - データの対象年（2020、2021など）
   - 時系列分析での期間識別

4. jobOfferRatio (Double)
   - 有効求人倍率（例：1.23倍、0.87倍）
   - 小数点以下の精度が重要なためDouble型を使用
   - マクロ経済指標として地域の労働市場を数値化

【マクロ経済学的意義】
- 地域経済の活性度を示す先行指標
- 雇用政策の効果測定に活用
- 地域間格差の定量的把握
- 産業構造と労働需給の関係分析

【期待される地域格差】
- 首都圏・中京圏：1.5倍前後（人手不足）
- 地方圏：0.8〜1.2倍（雇用機会の制約）
- 製造業集積地：高い数値（愛知県など）
- 過疎地域：低い数値（雇用創出課題）

【データ構造設計のポイント】
- PopulationData、WageDataと同一構造で一貫性確保
- Double型による精密な数値表現
- JSON変換での自動マッピング対応
- フロントエンドでの統一インターフェース実現
*/