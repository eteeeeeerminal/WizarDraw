# WizarDraw

## 実装することメモ
- CommandNavi
  - 素朴に実装する
  - 現在地はJLabelにtextと同時に追加できるIconで示す
- Palette(右上)
  - 描画ツールと現在の色、パレットの色を表示
  - パレットの色についてはCommandNaviにも表示
- Logと現在のモード情報は左上

- eventlistnerが1つしか登録できない問題は, EventMulticasterを実装することで解決できる.
- 内部で再帰的にリスナー起動することで, 1つのEventListnerで複数のEventを起動できる