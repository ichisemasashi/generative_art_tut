(ns generative_art_tut.gen_3_2_2
  (:require [quil.core :as q]))

;;;;; Processing用のソース;;;;;
; float xstep = 10;
; float ystep = 10;
; float lastx = 20;
; float lasty = 50;
; float y = 50;
; for (int x=20; x<= 480; x+=xstep) {
;   ystep = random(20) - 10;
;   y += ystep;
;   line(x,y,lastx, lasty);
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
  (q/save-frame "gen.3.2.2.jpg"))

(q/defsketch gen_3_2_2
  :title "successing micro line with rondom"
  :setup setup
  :size [500 100]
  )

