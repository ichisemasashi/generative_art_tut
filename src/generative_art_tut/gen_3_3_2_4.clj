(ns generative_art_tut.gen_3_3_2_4
  (:require [quil.core :as q]))

;;;;; Processing用のソース;;;;;
; size(500, 100);
; strokeWeight(4);
; float xstep = 1;
; float lastx = -999;
; float lasty = -999;
; float angle = 0;
; float y = 50;
; for(int x=20; x<=480;x+=xstep){
;   float rad = radians(angle);
;   y = 50 + (sin(rad) * 40);
;   if(lastx > -999) {
;     line(x, y, lastx, lasty);
;   }
;   lastx = x;
;   lasty = y;
;   angle++;
; }
;;;; ここまで ;;;;;
(defn- my-zip [xs ys]
  (map #(list %1 %2 %3 %4) xs ys (rest xs) (rest ys)))
(defn- my-rand [x]
  (- 1 (q/pow (rand 1) 5)))

(defn setup []
  (q/background 255)
  (q/stroke-weight 5)
  (q/smooth)

  (q/stroke 0 30)
  (q/line 20 50 480 50)

  (q/stroke 20 50 70)
  (let [step 3
        border-x 20
        xs (range border-x (- (q/width) border-x) step)
        ys (map #(+ 20 (* 60 (my-rand %))) (range))
        line-args (my-zip xs ys)]
        (dorun (map #(apply q/line %) line-args)))
  (q/save-frame "gen.3.3.2.4.jpg"))

(q/defsketch gen_3_3_2_4
  :title "sine^3 and noise curve"
  :setup setup
  :size [500 100]
  )

