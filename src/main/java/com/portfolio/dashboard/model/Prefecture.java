package com.portfolio.dashboard.model;

public class Prefecture {
  private String code;
  private String name;

  public Prefecture() {}

  public Prefecture(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

/*
========================================
Prefecture クラス解説
========================================

【クラス全体の役割】
- 都道府県情報を表現するデータモデルクラス（DTO: Data Transfer Object）
- 都道府県コードと都道府県名のペアを管理
- 将来的に都道府県一覧の選択肢生成や、データ検証に使用予定
- Spring MVCアーキテクチャのModel層を担当

【DTOとしての設計思想】
- シンプルなデータ保持に特化したクラス
- ビジネスロジックは含まず、純粋にデータの構造のみを定義
- 異なる層間でのデータ受け渡しの際の型安全性を確保
- 都道府県に関する情報を一元管理

【フィールドの説明】
private String code:
- 都道府県コード（例："13", "27", "14"）
- 総務省の全国地方公共団体コード（JIS X 0401）に基づく
- e-stat APIでのリクエスト時に使用する識別子
- 数値だが先頭ゼロの可能性があるため文字列で保持

private String name:
- 都道府県名（例："東京都", "大阪府", "神奈川県"）
- ユーザーインターフェースでの表示用
- HTML select要素のoption text として使用
- グラフの凡例やラベルとしても使用

【コンストラクタの説明】
public Prefecture():
- デフォルトコンストラクタ（引数なし）
- Springのデータバインディングで必要
- JSON⇔Javaオブジェクト変換時にフレームワークが使用
- 空のオブジェクトを生成後、setterで値を設定する場合に使用

public Prefecture(String code, String name):
- 全フィールドを初期化するコンストラクタ
- オブジェクト作成時に一度に全ての値を設定
- 都道府県マスターデータの初期化時に便利
- 例：new Prefecture("13", "東京都")

【getter/setterメソッドの役割】
JavaBeansの仕様に従ったアクセサメソッド

getCode(), setCode():
- 都道府県コードの取得/設定
- APIリクエスト時のパラメータ取得で使用
- 外部システムとの連携時の識別子として重要

getName(), setName():
- 都道府県名の取得/設定
- UI表示時の人間が読める形式での情報提供
- ログ出力や画面表示での可読性向上

【カプセル化の実装】
- private フィールド + public アクセサメソッドの組み合わせ
- 直接的なフィールドアクセスを禁止
- データの整合性を保つための設計パターン
- 将来的にバリデーションロジックを追加可能

【将来の拡張予定】
1. 都道府県マスターデータの管理:
   - 47都道府県の完全なリストを定数として定義
   - コードから名前への変換、名前からコードへの変換

2. バリデーション機能:
   - 有効な都道府県コードかどうかの検証
   - 都道府県名の正規化処理

3. 地域分類の追加:
   - 地方区分（関東、関西等）の情報
   - 人口規模による分類

4. HTMLフォーム生成支援:
   - select要素のoption生成
   - 都道府県選択UIの動的生成

【使用例】
// オブジェクト生成
Prefecture tokyo = new Prefecture("13", "東京都");

// フィールドアクセス
String code = tokyo.getCode();     // "13"
String name = tokyo.getName();     // "東京都"

// データ更新
Prefecture pref = new Prefecture();
pref.setCode("27");
pref.setName("大阪府");

【他のクラスとの関係】
- PopulationData: 都道府県情報を含むより詳細なデータモデル
- EstatApiService: Prefecture の code を使ってAPIリクエスト
- DashboardController: Prefecture のリストを画面に表示
- 将来的にはPrefectureのマスターデータとして活用予定

【設計パターンでの位置づけ】
- Value Object パターン: 値そのものを表現するオブジェクト
- Data Transfer Object パターン: レイヤー間のデータ転送
- JavaBeans パターン: 標準的なJavaオブジェクトの作り方

【JSON変換時の動作】
SpringBootでJSON変換時：
{
  "code": "13",
  "name": "東京都"
}
のような形式で出力される
*/