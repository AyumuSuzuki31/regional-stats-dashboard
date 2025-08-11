package com.portfolio.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

  @GetMapping("/")
  public String dashboard(Model model) {
    model.addAttribute("title", "都道府県データ比較ダッシュボード");
    return "dashboard";
  }
}

/*
========================================
DashboardController クラス解説
========================================

【クラス全体の役割】
- ユーザーからのWebリクエストを受け取り、適切なページを表示する
- Spring MVCのController層を担当
- ブラウザからのアクセスをHTMLテンプレートの表示に変換する

【アノテーションの説明】
@Controller:
- このクラスがSpringのWebコントローラーであることを宣言
- SpringBootが起動時に自動的にこのクラスを見つけて登録
- HTTPリクエストを処理できるクラスとして認識される

@GetMapping("/"):
- HTTP GETメソッドで"/"パス（ルートURL）にアクセスされた時の処理を定義
- つまり、http://localhost:8080/ にブラウザでアクセスすると実行される
- 他のHTTPメソッド用に@PostMapping, @PutMapping等もある

【dashboardメソッドの詳細】
引数 Model model:
- SpringBootが自動的に渡してくれるModelオブジェクト
- ControllerからView（HTMLテンプレート）にデータを渡すためのコンテナ
- key-value形式でデータを格納できる

model.addAttribute("title", "都道府県データ比較ダッシュボード"):
- Modelに"title"という名前でデータを追加
- HTMLテンプレート側で ${title} として参照可能
- 第1引数：テンプレート側で使う変数名
- 第2引数：実際にテンプレートに渡される値

return "dashboard":
- 表示するテンプレート名を文字列で返す
- SpringBootは自動的に"src/main/resources/templates/dashboard.html"を探す
- .html拡張子は自動的に補完される
- Thymeleafテンプレートエンジンがこのファイルを処理して最終HTMLを生成

【処理の流れ】
1. ユーザーがブラウザでhttp://localhost:8080/にアクセス
2. SpringBootが@GetMapping("/")を見つけてdashboardメソッドを実行
3. メソッド内でModelに"title"データを追加
4. "dashboard"という文字列を返す
5. SpringBootがdashboard.htmlテンプレートを探して読み込み
6. Thymeleafがテンプレート内の${title}を実際の値に置換
7. 最終的なHTMLがブラウザに送信されて表示

【Spring MVCアーキテクチャでの位置づけ】
- Model: Modelオブジェクト（データの受け渡し）
- View: dashboard.htmlテンプレート（表示層）
- Controller: DashboardControllerクラス（制御層）
*/

/*
========================================
Spring MVCアーキテクチャの解説
========================================

1. Model（モデル）：
   - データを管理する部分
   - 今回はModelオブジェクトでViewにデータを渡している

2. View（ビュー）：
   - ユーザーに表示される部分（HTML）
   - 今回はdashboard.htmlテンプレート

3. Controller（コントローラー）：
   - ユーザーのリクエストを受け取り、処理を制御する部分
   - 今回のDashboardControllerクラス

処理の流れ：
1. ユーザーがブラウザで http://localhost:8080/ にアクセス
2. SpringBootが@GetMapping("/")を見つけて、dashboardメソッドを実行
3. メソッド内でModelに"title"データを追加
4. "dashboard"という文字列を返す
5. SpringBootがdashboard.htmlテンプレートを探して表示
6. テンプレート内の th:text="${title}" が実際の値に置換される
7. 最終的なHTMLがブラウザに送信されて表示

========================================
アノテーションの詳細解説
========================================

@Controller：
- このクラスがWebリクエストを処理するコントローラーであることを示す
- SpringBootが起動時に自動的にこのクラスを見つけて登録する
- 内部的にはSpringのDI（依存性注入）コンテナに登録される

@GetMapping("/")：
- HTTPのGETメソッドで"/"パスにアクセスされた時の処理を定義
- 他にも@PostMapping, @PutMapping, @DeleteMappingなどがある
- RESTfulなWeb APIでは、HTTPメソッドによって処理を分ける

Model：
- SpringBootが自動的に提供するオブジェクト
- ControllerからView（テンプレート）にデータを渡すためのコンテナ
- addAttribute()でkey-value形式でデータを格納

========================================
今後追加予定の機能
========================================

このControllerに今後追加する予定：

1. API用のエンドポイント：
   @RestController や @ResponseBody を使った JSON API

2. 都道府県データ取得：
   @Serviceクラスと連携してe-stat APIからデータ取得

3. エラーハンドリング：
   @ExceptionHandler でエラー処理

4. バリデーション：
   @Valid や @RequestParam でリクエストデータの検証

5. パス変数の処理：
   @PathVariable で URL の一部を変数として受け取り
*/