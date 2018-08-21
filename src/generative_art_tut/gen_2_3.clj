(ns generative_art_tut.gen_2_3
  (:require [quil.core :as q]
            [quil.helpers.seqs :as h]))

;;;;; Processing用のソース;;;;;
; フレームループを利用。setup()とdraw()
; -- 設定 --
; int diam = 10; // 円の直径
; float centX, centY;
; 
; void setup() { // 始めに呼ばれる関数
; 	size(500,300);
; 	frameRate(24); // 毎秒24フレーム
; 	smooth();
; 	background(180);
; 	centX = width / 2;
; 	centY = height / 2;
; 	stroke(0);
; 	strokeWeight(5);
; 	fill(255, 50);
; }
; void draw() { // frameRate()で指定された数だけ呼ばれる関数
; 	if (diam <= 400) { // 直径のサイズが小さいこと
; 		background(180); //背景をクリア
; 		ellipse(centX, centY, diam, diam);
; 		diam += 10; // フレームループのたびに半径を広げる。
; 	}
; }
;;;; ここまで ;;;;;

(defn- bk_gray [] (q/background 180))
(defn- setup-win [] 
  (q/frame-rate 24)
  (q/smooth)
  (bk_gray))
(defn- setup-circle []
  (q/stroke 0)
  (q/stroke-weight 5)
  (q/no-fill))


(defn setup []
  (setup-win)
  (setup-circle)
;  (let [diams (h/range-incl 10 400 10)]
    (q/set-state! :diam (h/seq->stream (range 10 400 10))
                  :cent-x (/ (q/width) 2)
                  :cent-y (/ (q/height) 2)))

(defn draw []
  (let [cent-x (q/state :cent-x)
        cent-y (q/state :cent-y)
        diam   ((q/state :diam))]
    (when diam
      (bk_gray)
      (q/ellipse cent-x cent-y diam diam))))

(q/defsketch gen_2_3
  :title "Growing circle"
  :setup setup
  :draw draw
  :size [500 300]
  ;:keep-on-top true
  )

