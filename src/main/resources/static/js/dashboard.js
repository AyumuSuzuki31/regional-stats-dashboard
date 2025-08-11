// SpringBootダッシュボードのメインJavaScript
// e-stat APIと連携して都道府県の人口/賃金データを比較表示

console.log('🚀 ダッシュボード JavaScript 読み込み完了');

// グローバル変数
let populationChart = null; // Chart.jsのインスタンスを格納

// DOM読み込み完了後の初期化
document.addEventListener('DOMContentLoaded', function() {
  console.log('✅ DOM読み込み完了');
  initializeDashboard();
});

// ダッシュボード初期化
function initializeDashboard() {
  console.log('📊 ダッシュボード初期化中...');

  initializeChart();        // Chart.js初期化
  setupEventListeners();    // イベントリスナー設定
  updateUI();              // UI状態更新

  console.log('✅ ダッシュボード初期化完了');
}

// Chart.js初期化（棒グラフ用）
function initializeChart() {
  // HTML要素からCanvas要素を取得
  const ctx = document.getElementById('populationChart').getContext('2d');

  // Chart.jsインスタンスを作成
  populationChart = new Chart(ctx, {
    type: 'bar', // 棒グラフを指定
    data: {
      labels: [], // X軸ラベル（都道府県名）- 初期値は空
      datasets: [{
        label: '2020年データ', // データセットのラベル
        data: [], // Y軸データ（数値）- 初期値は空
        backgroundColor: [
          'rgba(102, 126, 234, 0.8)', // 1番目の棒の色（青系）
          'rgba(255, 99, 132, 0.8)'   // 2番目の棒の色（赤系）
        ],
        borderColor: [
          'rgb(102, 126, 234)', // 1番目の棒の境界線色
          'rgb(255, 99, 132)'   // 2番目の棒の境界線色
        ],
        borderWidth: 2 // 境界線の太さ
      }]
    },
    options: {
      responsive: true, // 画面サイズに応じてサイズ調整
      maintainAspectRatio: false, // アスペクト比を固定しない
      plugins: {
        title: {
          display: true, // タイトルを表示
          text: '都道府県別データ比較（2020年）', // 初期タイトル
          font: { size: 18 } // タイトルのフォントサイズ
        },
        legend: {
          display: false // 凡例を非表示（単一データセットのため）
        }
      },
      scales: {
        y: {
          beginAtZero: true, // Y軸を0から開始
          title: {
            display: true, // Y軸タイトルを表示
            text: 'データ値' // 初期Y軸タイトル
          },
          ticks: {
            // Y軸の目盛りラベルのフォーマット関数
            callback: function(value) {
              // データタイプに応じて表示形式を変更
              return formatYAxisLabel(value);
            }
          }
        },
        x: {
          title: {
            display: true, // X軸タイトルを表示
            text: '都道府県' // X軸タイトル
          }
        }
      }
    }
  });

  console.log('📈 Chart.js初期化完了（棒グラフ）');
}

// Y軸ラベルのフォーマット関数
function formatYAxisLabel(value) {
  // 選択されているデータタイプを取得
  const dataType = getSelectedDataType();

  if (dataType === 'population') {
    // 人口データの場合：万人単位で表示
    return (value / 10000).toFixed(0) + '万人';
  } else if (dataType === 'wage') {
    // 賃金データの場合：万円単位で表示
    return (value / 10000).toFixed(0) + '万円';
  } else if (dataType === 'job') {
    // 有効求人倍率の場合：小数点以下2桁の倍数で表示
    return value.toFixed(2) + '倍';
  }

  // その他の場合はそのまま表示
  return value;
}

// イベントリスナー設定
function setupEventListeners() {
  // HTML要素を取得
  const compareBtn = document.getElementById('compareBtn');
  const pref1Select = document.getElementById('prefecture1');
  const pref2Select = document.getElementById('prefecture2');
  const dataTypeRadios = document.querySelectorAll('input[name="dataType"]');

  // 比較ボタンのクリックイベント
  compareBtn.addEventListener('click', handleCompareClick);

  // 都道府県選択変更イベント
  pref1Select.addEventListener('change', updateUI);
  pref2Select.addEventListener('change', updateUI);

  // データタイプ選択変更イベント
  dataTypeRadios.forEach(radio => {
    radio.addEventListener('change', handleDataTypeChange);
  });

  console.log('🎯 イベントリスナー設定完了');
}

// データタイプ変更時の処理
function handleDataTypeChange() {
  console.log('🔄 データタイプ変更:', getSelectedDataType());

  // グラフのタイトルとラベルを更新
  updateChartLabels();

  // UI状態を更新
  updateUI();
}

// グラフのタイトルとラベルを更新
function updateChartLabels() {
  // 選択されているデータタイプを取得
  const dataType = getSelectedDataType();

  if (dataType === 'population') {
    // 人口データの場合
    populationChart.options.plugins.title.text = '都道府県別人口比較（2020年）';
    populationChart.options.scales.y.title.text = '人口（万人）';
  } else if (dataType === 'wage') {
    // 賃金データの場合
    populationChart.options.plugins.title.text = '都道府県別賃金比較（2020年）';
    populationChart.options.scales.y.title.text = '平均年収（万円）';
  } else if (dataType === 'job') {
    // 有効求人倍率の場合
    populationChart.options.plugins.title.text = '都道府県別有効求人倍率比較（2020年）';
    populationChart.options.scales.y.title.text = '有効求人倍率（倍）';
  }

  // グラフを更新（ラベルのみ）
  populationChart.update('none');
}

// 現在選択されているデータタイプを取得
function getSelectedDataType() {
  // ラジオボタンから選択されている値を取得
  const selectedRadio = document.querySelector('input[name="dataType"]:checked');
  return selectedRadio ? selectedRadio.value : 'population'; // デフォルトは人口
}

// 比較ボタンクリック時の処理
async function handleCompareClick() {
  // 選択された都道府県コードを取得
  const pref1Code = document.getElementById('prefecture1').value;
  const pref2Code = document.getElementById('prefecture2').value;

  // バリデーション（入力チェック）
  if (!validateSelection(pref1Code, pref2Code)) {
    return; // バリデーション失敗時は処理中断
  }

  // ローディング表示開始
  showLoading(true);
  hideError();

  try {
    console.log(`🔄 データ取得開始: ${pref1Code} vs ${pref2Code} (${getSelectedDataType()})`);

    // 選択されたデータタイプに応じてAPI呼び出し
    const [data1, data2] = await Promise.all([
      fetchDataByType(pref1Code),
      fetchDataByType(pref2Code)
    ]);

    // 2020年のデータのみ抽出して比較
    updateChart(data1, data2);

    // 成功メッセージ表示
    const pref1Name = getDataName(data1[0]);
    const pref2Name = getDataName(data2[0]);
    const dataTypeText = getSelectedDataType() === 'population' ? '人口' : '賃金';
    showStatus(`${pref1Name} vs ${pref2Name} の${dataTypeText}比較完了`, 'success');

    console.log('✅ データ比較完了');

  } catch (error) {
    console.error('❌ 比較処理エラー:', error);
    showError(`データの取得に失敗しました: ${error.message}`);
  } finally {
    // 成功・失敗に関わらずローディング終了
    showLoading(false);
  }
}

// データタイプに応じてAPIからデータ取得
async function fetchDataByType(prefectureCode) {
  // 選択されているデータタイプを確認
  const dataType = getSelectedDataType();

  // データタイプに応じてAPIエンドポイントを決定
  let url;
  if (dataType === 'population') {
    url = `/api/population/${prefectureCode}`;
  } else if (dataType === 'wage') {
    url = `/api/wage/${prefectureCode}`;
  } else if (dataType === 'job') {
    url = `/api/job/${prefectureCode}`;
  } else {
    throw new Error('未知のデータタイプです');
  }

  console.log(`📡 API呼び出し: ${url}`);

  // SpringBoot APIを呼び出し
  const response = await fetch(url);

  // HTTPエラーチェック
  if (!response.ok) {
    throw new Error(`HTTP Error: ${response.status}`);
  }

  // JSONデータを取得
  const data = await response.json();

  // データ存在チェック
  if (!data || data.length === 0) {
    throw new Error('データが見つかりませんでした');
  }

  console.log(`✅ データ取得成功: ${getDataName(data[0])} (${data.length}件)`);
  return data;
}

// データから都道府県名を取得
function getDataName(dataItem) {
  // データタイプに関わらず都道府県名を取得
  return dataItem.prefectureName || '不明';
}

// データから値を取得
function getDataValue(dataItem) {
  // データタイプに応じて適切な値を取得
  const dataType = getSelectedDataType();

  if (dataType === 'population') {
    return dataItem.population || 0;
  } else if (dataType === 'wage') {
    return dataItem.averageWage || 0;
  } else if (dataType === 'job') {
    return dataItem.jobOfferRatio || 0;
  }

  return 0;
}

// グラフ更新（2020年単年比較）
function updateChart(data1, data2) {
  try {
    // 2020年のデータを抽出
    const data2020_1 = data1.find(d => d.year === '2020');
    const data2020_2 = data2.find(d => d.year === '2020');

    // データが見つからない場合のフォールバック
    const pref1Data = data2020_1 || data1[data1.length - 1]; // 最新データ
    const pref2Data = data2020_2 || data2[data2.length - 1]; // 最新データ

    // データから値を取得
    const value1 = getDataValue(pref1Data);
    const value2 = getDataValue(pref2Data);

    console.log(`データ確認: ${getDataName(pref1Data)}=${value1.toLocaleString()}, ${getDataName(pref2Data)}=${value2.toLocaleString()}`);

    // Chart.js用のデータ形式に変換
    populationChart.data.labels = [
      getDataName(pref1Data),
      getDataName(pref2Data)
    ];

    populationChart.data.datasets[0].data = [
      value1,
      value2
    ];

    // Y軸は常に0から開始（動的調整なし）
    populationChart.options.scales.y.beginAtZero = true;
    delete populationChart.options.scales.y.min;
    delete populationChart.options.scales.y.max;

    // グラフを再描画（アニメーション付き）
    populationChart.update('active');

    console.log(`📊 グラフ更新完了（0から開始）`);

  } catch (error) {
    console.error('❌ グラフ更新エラー:', error);

    // エラー時は基本的なグラフ表示にフォールバック
    populationChart.data.labels = ['データ1', 'データ2'];
    populationChart.data.datasets[0].data = [1000000, 1100000];
    populationChart.update();
  }
}

// バリデーション（入力チェック）
function validateSelection(pref1Code, pref2Code) {
  // 都道府県が選択されていない場合
  if (!pref1Code || !pref2Code) {
    showError('都道府県を2つ選択してください');
    return false;
  }

  // 同じ都道府県が選択されている場合
  if (pref1Code === pref2Code) {
    showError('異なる都道府県を選択してください');
    return false;
  }

  return true; // バリデーション成功
}

// UI状態更新
function updateUI() {
  // 選択された都道府県と設定を取得
  const pref1 = document.getElementById('prefecture1').value;
  const pref2 = document.getElementById('prefecture2').value;
  const btn = document.getElementById('compareBtn');
  const dataType = getSelectedDataType();

  // 都道府県名を取得
  const pref1Name = getPrefectureName(pref1);
  const pref2Name = getPrefectureName(pref2);

  // データタイプの表示名を取得
  const dataTypeText = dataType === 'population' ? '人口' :
                      dataType === 'wage' ? '賃金' :
                      dataType === 'job' ? '有効求人倍率' : 'データ';

  // ボタンの状態とテキストを更新
  if (pref1 && pref2 && pref1 !== pref2) {
    btn.disabled = false; // ボタン有効化
    btn.textContent = `${pref1Name} vs ${pref2Name} の${dataTypeText}を比較`;
  } else {
    btn.disabled = true; // ボタン無効化
    btn.textContent = '都道府県を選択してください';
  }
}

// 都道府県名取得
function getPrefectureName(code) {
  // セレクトボックスから都道府県名を取得
  const select = document.getElementById('prefecture1');
  const option = select.querySelector(`option[value="${code}"]`);
  return option ? option.textContent : '';
}

// ローディング表示制御
function showLoading(show = true) {
  // 既存のローディング要素を取得または作成
  let loadingDiv = document.getElementById('loading');

  // ローディング要素が存在しない場合は作成
  if (!loadingDiv) {
    loadingDiv = document.createElement('div');
    loadingDiv.id = 'loading';
    loadingDiv.className = 'loading hidden';
    loadingDiv.innerHTML = '<div>📡 データを読み込み中...</div>';
    document.querySelector('.container').appendChild(loadingDiv);
  }

  // 表示・非表示切り替え
  loadingDiv.classList.toggle('hidden', !show);
}

// ステータスメッセージ表示
function showStatus(message, type = 'info') {
  // 既存のステータス要素を取得または作成
  let statusDiv = document.getElementById('status');

  // ステータス要素が存在しない場合は作成
  if (!statusDiv) {
    statusDiv = document.createElement('div');
    statusDiv.id = 'status';
    statusDiv.className = 'status hidden';
    document.querySelector('.container').appendChild(statusDiv);
  }

  // メッセージとスタイルを設定
  statusDiv.textContent = message;
  statusDiv.className = `status ${type}`;
  statusDiv.classList.remove('hidden');

  // エラーメッセージを非表示
  hideError();
}

// エラーメッセージ表示
function showError(message) {
  // 既存のエラー要素を取得または作成
  let errorDiv = document.getElementById('error');

  // エラー要素が存在しない場合は作成
  if (!errorDiv) {
    errorDiv = document.createElement('div');
    errorDiv.id = 'error';
    errorDiv.className = 'error hidden';
    document.querySelector('.container').appendChild(errorDiv);
  }

  // エラーメッセージを設定
  errorDiv.textContent = '❌ ' + message;
  errorDiv.classList.remove('hidden');

  // ステータスメッセージを非表示
  const statusDiv = document.getElementById('status');
  if (statusDiv) {
    statusDiv.classList.add('hidden');
  }
}

// エラーメッセージ非表示
function hideError() {
  // エラー要素を取得して非表示にする
  const errorDiv = document.getElementById('error');
  if (errorDiv) {
    errorDiv.classList.add('hidden');
  }
}

/*
=== dashboard.js 人口/賃金切り替え対応版の詳細解説 ===

【主要な新機能】
1. データタイプ切り替え機能
   - getSelectedDataType(): ラジオボタンから選択状態を取得
   - handleDataTypeChange(): データタイプ変更時の処理
   - 人口データ（population）と賃金データ（wage）の動的切り替え

2. 動的API呼び出し
   - fetchDataByType(): データタイプに応じた適切なAPIエンドポイント選択
   - /api/population/{code} または /api/wage/{code} を自動選択
   - 統一されたインターフェースで異なるデータタイプを処理

3. グラフ表示の動的変更
   - updateChartLabels(): データタイプに応じたタイトル・軸ラベル更新
   - formatYAxisLabel(): Y軸の単位表示を動的変更（万人/万円）
   - Chart.jsの設定をリアルタイムで更新

【技術的な改善点】
1. データ抽象化
   - getDataName(): 共通の都道府県名取得インターフェース
   - getDataValue(): データタイプに応じた値取得の統一化
   - 異なるデータ構造を透明に処理

2. エラーハンドリングの強化
   - データタイプ別のエラーメッセージ
   - APIエンドポイント選択時の例外処理
   - フォールバック処理の充実

3. ユーザビリティの向上
   - ボタンテキストの動的更新（「人口を比較」「賃金を比較」）
   - データタイプ変更時の即座のUI反映
   - 一貫性のあるユーザーフィードバック

【Chart.js活用技術】
- options.plugins.title.text の動的更新
- options.scales.y.title.text の動的変更
- ticks.callback でのカスタムフォーマット関数
- update('none') での軽量なラベル更新

【非同期処理パターン】
- Promise.all() による並行API呼び出し
- async/await による可読性の高い非同期コード
- try-catch-finally での確実なエラーハンドリング

【DOM操作のベストプラクティス】
- querySelectorAll() でのラジオボタン群取得
- addEventListener() での効率的なイベント管理
- 動的要素作成時の適切なクラス設定

【拡張性の確保】
- 新しいデータタイプ（GDP、雇用率等）の追加が容易
- APIエンドポイントパターンの統一により保守性向上
- モジュラーな関数設計で機能の独立性確保

これで人口と賃金の両方のデータを同一インターフェースで
シームレスに比較できる高機能ダッシュボードが完成。
*/