(ns generative_art_tut.gen_3_1_1
  (:require [quil.core :as q]
            [quil.helpers.seqs :as h]))

;;;;; Processing用のソース;;;;;
; size(500, 100);
; background(255);
; strokeWeight(5); // 線の太さ
; smooth();
; 
; stroke(0, 30); // 線の色
; line(20,50,480,50);
; 
; stroke(20, 50, 70);
; float randx = random(width); // 0～widthまでのrandom
; float randy = random(height);
; line(20,50,randx,randy);
;;;; ここまで ;;;;;

(defn- bk_w [] (q/background 255))
(defn- ln [x y] (q/line 20 50 x y))
(defn- normal-line [x y] 
  (q/stroke 0 30)
  (ln x y))
(defn- random-line []
  (let [x (q/random (q/width)), y (q/random (q/height))]
    (q/stroke 20 50 70)
    (ln x y)))
(defn- setup-line []
  (q/stroke-weight 5))

(defn setup []
  (bk_w)
  (q/smooth)
  (setup-line)
  (normal-line 480 50)
  (random-line)
  (q/save-frame "gen.3.1.1.jpg"))

(q/defsketch gen_3_1_1
  :title "random Lines"
  :setup setup
  :size [500 100]
  )

