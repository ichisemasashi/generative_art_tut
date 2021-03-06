(ns generative_art_tut.gen_3_2_1
  (:require [quil.core :as q]))

;;;;; Processing用のソース;;;;;
; int step = 10;
; float lastx = -999;
; float lasty = -999;
; float y = 50;
; int borderx = 20;
; int bordery = 10;
; for (int x=borderx; x<=width-borderx;x+=step) {
;   y = bordery + random(height - 2*bordery);
;   if(lastx > -999) {
;     line(x, y, lastx, lasty);
;   }
;   lastx = x;
;   lasty = y;
; }
;;;; ここまで ;;;;;
(defn- my-zip [xs ys]
  (map #(list %1 %2 %3 %4) xs ys (rest xs) (rest ys)))
(defn- rand-y [y]
  (let [h (q/height)
        seed (- h (* 2 y))]
    (+ y (rand seed))))

(defn setup []
  (let [step 10
        border-x 20
        border-y 10
        xs (range border-x (- (q/width) border-x) step)
        ys (repeatedly #(rand-y border-y))
        line-args (my-zip xs ys)]
        (dorun (map #(apply q/line %) line-args)))
  (q/save-frame "gen.3.2.1.jpg"))

(q/defsketch gen_3_2_1
  :title "successing micro line with rondom"
  :setup setup
  :size [500 100]
  )

