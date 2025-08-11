# 📊 都道府県別経済指標ダッシュボード

SpringBoot + Chart.jsで構築した、日本の地域経済格差を可視化するWebアプリケーション

## 🌐 デモ

**本番環境**: https://regional-stats-dashboard-production.up.railway.app

![ダッシュボード画面](https://img.shields.io/badge/Status-Live-brightgreen)
![SpringBoot](https://img.shields.io/badge/SpringBoot-3.x-brightgreen)
![Java](https://img.shields.io/badge/Java-17-blue)
![Chart.js](https://img.shields.io/badge/Chart.js-4.x-orange)

## 🎯 プロジェクト概要

### 課題
- 日本の地域間経済格差が数値として見えにくい
- 複数の経済指標を統合的に比較する手段の不足
- マクロ経済データの可視化ツールの必要性

### 解決策
47都道府県の主要経済指標を**リアルタイム比較**できるダッシュボードを開発

### 特徴
- 📊 **3つの重要指標**: 人口・賃金・有効求人倍率
- 🎨 **直感的UI**: Chart.jsによる美しいグラフ表示
- 🔄 **リアルタイム切り替え**: データタイプの瞬時切り替え
- 📱 **レスポンシブ**: モバイル端末対応
- 🌏 **47都道府県対応**: 全国データ網羅

## 🛠️ 技術スタック

### Backend
- **SpringBoot 3.x** - Java Webアプリケーションフレームワーク
- **Thymeleaf** - サーバーサイドテンプレートエンジン
- **Maven** - プロジェクト管理・ビルドツール

### Frontend
- **Chart.js 4.x** - データ可視化ライブラリ
- **Vanilla JavaScript** - インタラクティブ機能
- **CSS3** - レスポンシブデザイン

### Infrastructure
- **Railway** - クラウドデプロイメントプラットフォーム
- **GitHub** - ソースコード管理
- **e-stat API** - 政府統計データ連携（予定）

## 📁 プロジェクト構造

```
regional-stats-dashboard/
├── src/
│   ├── main/
│   │   ├── java/com/portfolio/dashboard/
│   │   │   ├── controller/          # REST API・画面制御
│   │   │   │   ├── ApiController.java
│   │   │   │   └── DashboardController.java
│   │   │   ├── model/               # データモデル
│   │   │   │   ├── PopulationData.java
│   │   │   │   ├── WageData.java
│   │   │   │   ├── JobData.java
│   │   │   │   └── Prefecture.java
│   │   │   ├── service/             # ビジネスロジック
│   │   │   │   └── EstatApiService.java
│   │   │   └── RegionalStatsDashboardApplication.java
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── css/dashboard.css
│   │       │   └── js/dashboard.js
│   │       ├── templates/dashboard.html
│   │       └── application.properties
│   └── test/                        # テストファイル
├── .gitignore
├── pom.xml
└── README.md
```

## 🚀 ローカル開発

### 前提条件
- Java 17以上
- Maven 3.6以上
- IntelliJ IDEA (推奨)

### セットアップ手順

1. **リポジトリクローン**
```bash
git clone https://github.com/AyumuSuzuki31/regional-stats-dashboard.git
cd regional-stats-dashboard
```

2. **依存関係インストール**
```bash
mvn clean install
```

3. **アプリケーション起動**
```bash
mvn spring-boot:run
```

4. **ブラウザでアクセス**
```
http://localhost:8080
```

### 開発環境での確認
- **ダッシュボード**: http://localhost:8080
- **人口API**: http://localhost:8080/api/population/13
- **賃金API**: http://localhost:8080/api/wage/13
- **求人API**: http://localhost:8080/api/job/13

## 📊 提供データ

### 人口データ
- **対象年**: 2015年、2020年
- **単位**: 人
- **例**: 東京都 14,047,594人 (2020年)

### 賃金データ
- **対象年**: 2015年、2020年
- **単位**: 円（年収）
- **例**: 東京都 6,220,000円 (2020年)

### 有効求人倍率
- **対象年**: 2015年、2020年
- **単位**: 倍
- **例**: 福井県 1.67倍 (2020年)

## 🎨 主要機能

### 1. データタイプ切り替え
ラジオボタンで瞬時に表示データを切り替え
- 📊 人口データ
- 💰 賃金データ  
- 💼 有効求人倍率

### 2. 都道府県比較
- 47都道府県から2つを選択
- リアルタイム棒グラフ表示
- 格差の可視化

### 3. レスポンシブデザイン
- デスクトップ・タブレット・スマートフォン対応
- 美しいアニメーション効果

## 📈 発見される地域格差例

### 賃金格差
- **東京都**: 622万円
- **沖縄県**: 368万円
- **格差**: 254万円 (約1.7倍)

### 労働市場格差
- **福井県**: 1.67倍 (人手不足)
- **沖縄県**: 0.87倍 (求職者過多)
- **格差**: 約2倍

## 🔮 今後の機能拡張

### 予定機能
- [ ] **GDP(県内総生産)** データ追加
- [ ] **完全失業率** データ追加
- [ ] **時系列グラフ** 対応
- [ ] **実際のe-stat API** 連携
- [ ] **データエクスポート** 機能
- [ ] **地図ビュー** 表示

### 技術的改善
- [ ] **Spring Security** 導入
- [ ] **PostgreSQL** データベース連携
- [ ] **Docker** 対応
- [ ] **CI/CD** パイプライン構築

## 👤 開発者

**鈴木歩夢 (Ayumu Suzuki)**
- GitHub: [@AyumuSuzuki31](https://github.com/AyumuSuzuki31)
- 開発期間: 2025年8月 (プログラミング学習開始: 2025年7月)
- 興味分野: マクロ経済学、データ可視化、フルスタック開発

## 📄 ライセンス

このプロジェクトはMITライセンスの下で公開されています。

## 🙏 謝辞

- **e-stat (政府統計の総合窓口)** - 統計データ提供
- **Chart.js** - 美しいグラフライブラリ
- **Railway** - 簡単デプロイメント
- **SpringBoot** - 強力なJavaフレームワーク

---

⭐ このプロジェクトが役に立った場合は、スターをつけていただけると嬉しいです！

📧 質問やフィードバックがあれば、Issueやプルリクエストでお気軽にご連絡ください。
