(ns generative_art_tut.gen_2_2
  (:require [quil.core :as q]
            [quil.middleware :as m]))

;;;;; Processing用のソース;;;;;
; -- 設定と背景 --
; size(500, 300);キャンバスのサイズを500(pix)x300(pix)に設定
; smooth();描く直線にアンチエイリアスをかけて、ガタガタに見えないようにする設定
; background(230, 230, 230);  背景色をグレーに設定。パラメタはRGB
; -- 2本の交差した直線を描く --
; stroke(130, 0, 0); 線の色を赤に設定(RGBのRだけ値があるから。)第四パラにアルファ値を設定できる。
; strokeWeight(4); 線の太さを4ピクセルに設定
; line(width/2 - 70, height/2 - 70, width/2 + 70, height/2 + 70);設定したstrokeのスタイルで線を引く。
;   引数は(始点のx座標, 始点のy座標, 終点のx座標, 終点のy座標)
;   width, heightはそれぞれ、幅と高さ
; line(width/2 + 70, height/2 - 70, width/2 - 70, height/2 + 70); 2つめの線を引く。
; -- 円を描く --
; fill(255, 150); 円の内側を塗り潰す設定。第一パラはグレースケールの色。第二パラはアルファ値。
; ellipse(width/2, height/2, 50, 50); 円をかく。
;;;; ここまで ;;;;;

(defn- bk_gray [] (let [g 230] (q/background g g g)))
(defn- sk_red [] (let [r 130, z 0] (q/stroke r z z)))
(defn- sk_4 [] (let [c 4] (q/stroke-weight c)))
(defn- fill_el [] (let [a 255, b 150] (q/fill a b)))
(defn- setup-win []
  ; size() -- not support, use :size[].
  (q/smooth)
  (bk_gray))
(defn- setup-line []
  (sk_red)
  (sk_4))
(defn- setup-ellips []
  (fill_el))
(defn setup []
  (setup-win)
  (setup-line)
  (setup-ellips))
(defn- half [i]
  (/ i 2))
(defn- closs-the-lines [w h]
  (let [w (half w), h (half h), d 70]
    (q/line (- w d) (- h d) (+ w d) (+ h d))  ; \
    (q/line (+ w d) (- h d) (- w d) (+ h d)))); /
(defn- draw-the-circle [w h]
  (let [w (half w), h (half h), d 50]
    (q/ellipse w h d d)))



(defn draw-my-art []
  (let [w (q/width), h (q/height)]
    (closs-the-lines w h)
    (draw-the-circle w h))
  (q/save-frame "gen.2.2.jpg"))

(q/defsketch gen_2_2
  :title "Cross with circle"
  :size [500 300]
  :setup setup
  :draw draw-my-art
  )