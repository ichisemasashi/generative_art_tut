;;;;; Processing用のソース;;;;;
; フレームループを利用。setup()とdraw()
; -- 設定 --
int diam = 10; // 円の直径
float centX, centY;

void setup() { // 始めに呼ばれる関数
	size(500,300);
	frameRate(24); // 毎秒24フレーム
	smooth();
	background(180);
	centX = width / 2;
	centY = height / 2;
	stroke(0);
	strokeWeight(5);
	fill(255, 50);
}
void draw() { // frameRate()で指定された数だけ呼ばれる関数
	if (diam <= 400) { // 直径のサイズが小さいこと
		background(180); //背景をクリア
		ellipse(centX, centY, diam, diam);
		diam += 10; // フレームループのたびに半径を広げる。
	}
}
; background(230, 230, 230);  背景色をグレーに設定
; -- 2本の交差した直線を描く --
; stroke(130, 0, 0); 線の色を赤に設定(RGBのRだけ値があるから。)
; strokeWeight(4); 線の太さを4ピクセルに設定
; line(width/2 - 70, height/2 - 70, width/2 + 70, height/2 + 70);設定したstrokeのスタイルで線を引く。
;   引数は(始点のx座標, 始点のy座標, 終点のx座標, 終点のy座標)
;   width, heightはそれぞれ、幅と高さ
; line(width/2 + 70, height/2 - 70, width/2 - 70, height/2 + 70); 2つめの線を引く。
; -- 円を描く --
; fill(255, 150); 円の内側を塗り潰す設定
; ellipse(width/2, height/2, 50, 50); 円をかく。
;;;; ここまで ;;;;;
