package com.portfolio.dashboard.controller;

import com.portfolio.dashboard.model.JobData;
import com.portfolio.dashboard.model.PopulationData;
import com.portfolio.dashboard.model.WageData;
import com.portfolio.dashboard.service.EstatApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

  @Autowired
  private EstatApiService estatApiService;

  @GetMapping("/population/{prefectureCode}")
  public List<PopulationData> getPopulationData(@PathVariable String prefectureCode) {
    return estatApiService.getPopulationData(prefectureCode);
  }

  @GetMapping("/wage/{prefectureCode}")
  public List<WageData> getWageData(@PathVariable String prefectureCode) {
    return estatApiService.getWageData(prefectureCode);
  }

  @GetMapping("/job/{prefectureCode}")
  public List<JobData> getJobData(@PathVariable String prefectureCode) {
    return estatApiService.getJobData(prefectureCode);
  }
}

/*
=== ApiController有効求人倍率対応版の詳細解説 ===

【追加されたエンドポイント】
/api/job/{prefectureCode}
- GET方式でアクセス
- 都道府県コード（01-47）をパスパラメータで受け取り
- JobDataのListをJSON形式で返却

【エンドポイント体系】
- /api/population/{code} - 人口データ
- /api/wage/{code} - 賃金データ
- /api/job/{code} - 有効求人倍率データ

【レスポンス例（東京都の有効求人倍率）】
[
  {
    "prefectureCode": "13",
    "prefectureName": "東京都",
    "year": "2015",
    "jobOfferRatio": 1.3775
  },
  {
    "prefectureCode": "13",
    "prefectureName": "東京都",
    "year": "2020",
    "jobOfferRatio": 1.45
  }
]

【RESTful API設計の完成】
- 統一されたURL構造（/api/{dataType}/{code}）
- 一貫性のあるレスポンス形式
- 拡張性の高いエンドポイント設計

【マクロ経済分析での活用】
フロントエンドから以下のような分析が可能：
- 雇用環境の地域格差把握
- 賃金水準と求人倍率の相関分析
- 人口流動要因の多角的検証
- 地方創生政策の効果測定

【システム拡張性】
新しい経済指標も同じパターンで追加可能：
- /api/gdp/{code} - 県内総生産
- /api/unemployment/{code} - 完全失業率
- /api/manufacturing/{code} - 製造業出荷額

これで3つの重要な経済指標を統一APIで提供する
本格的な経済データプラットフォームが完成。
*/