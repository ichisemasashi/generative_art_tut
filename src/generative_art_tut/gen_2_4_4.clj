(ns generative_art_tut.gen_2_4_4
  (:require [quil.core :as q]
            [quil.helpers.seqs :as h]))

;;;;; Processing用のソース;;;;;
; void setup() {
;   size(500,300);
;   background(180);
;   strokeWeight(4);
;   strokeCap(SQUARE);
;   for (int h = 10; h <= (height - 15); h+=10) {
;     stroke(0, 255-h);
;     line(10, h, width - 20, h);
;     stroke(255,h);
;     line(10,h+4,width-20, h+4);
;   }
; }
;;;; ここまで ;;;;;

(defn- bk_gray [] (q/background 180))
(defn- draw-line [h]
  (let [w (q/width)]
    (q/stroke 0 (- 255 h))
    (q/line 10 h (- w 20) h)
    (q/stroke 255 h)
    (q/line 10 (+ h 4) (- w 20) (+ h 4))
    ))

(defn setup []
  (bk_gray)
  (q/stroke-weight 4)
  (q/stroke-cap :square)
  (let [line-heights (range 10 (- (q/height) 15) 10)]
    (dorun (map draw-line line-heights)))
  (q/save-frame "gen.2.4.4.jpg"))

(q/defsketch gen_2_4_4
  :title "Fading Horizontal Lines"
  :setup setup
  :size [500 300]
  )

