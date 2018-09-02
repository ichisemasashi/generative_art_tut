(ns generative_art_tut.gen_3_3
  (:require [quil.core :as q]))

;;;;; Processing用のソース;;;;;
; size(500, 100);
; background(255);
; strokeWeight(5);
; smooth();
; 
; stroke(0, 30);
; line (20, 50, 480, 50);
; 
; stroke(20, 50, 70);
; int step = 10;
; float lastx = -999;
; float lasty = -999;
; float ynoise = random(10);
; float y;
; for (int x=20; x<=480; x+=step) {
;   y = 10 + noise(ynoise) * 80;
;   if (lastx > -999) {
;     line(x, y, lastx, lasty);
;   }
;   lastx = x;
;   lasty = y;
;   ynoise += 0.1;
; }
;;;; ここまで ;;;;;
(defn- my-zip [xs ys]
  (map #(list %1 %2 %3 %4) xs ys (rest xs) (rest ys)))
(defn- rand-seq [x]
  (let [d 0.1]
    (lazy-seq (cons (q/noise x)
                    (rand-seq  (+ d x))))))

(defn setup []
  (q/background 255)
  (q/stroke-weight 5)
  (q/smooth)

  (q/stroke 0 30)
  (q/line 20 50 480 50)

  (q/stroke 20 50 70)
  (let [step 10
        border-x 20
        seed (rand 10)
        xs (range border-x (- (q/width) border-x) step)
        ys (map #(* % 80) (rand-seq seed))
        line-args (my-zip xs ys)]
        (dorun (map #(apply q/line %) line-args)))
  (q/save-frame "gen.3.3.jpg"))

(q/defsketch gen_3_2_3
  :title "successing micro line with noise"
  :setup setup
  :size [500 100]
  )

