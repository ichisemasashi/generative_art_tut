(ns generative_art_tut.gen_4_1_3_1
  (:require [quil.core :as q]))

;;;;; Processing用のソース;;;;;
; size(500, 300);
; background(255);
; strokeWeight(0.5);
; smooth();
; int centx = 250;
; int centy = 150;
; float x, y;
; for (int i = 0; i <100; i++) {
;   float lastx = -999;
;   float lasty = -999;
;   float radiusNoise = random(10);
;   float radius = 10;
;   stroke(random(20), random(50), random(70), 80);
; 
;   int startangle = int(random(360));
;   int endangle = 1440 + int(random(1440));
;   int anglestep = 5 + int(random(3));
;   for (float ang = startangle;ang<=endangle;ang+=anglestep) {
;     radiusNoise += 0.05;
;     radius += 0.5;
;     float thisRadius = radius + (noise(radiusNoise) * 200) - 100;
;     float rad = radians(ang);
;     x = centx + (thisRadius * cos(rad));
;     y = centy + (thisRadius * sin(rad));
;     if(lastx > -999) {
;       line(x, y, lastx, lasty);
;     }
;     lastx = x;
;     lasty = y;
;   }
; }
;;;; ここまで ;;;;;
(defn- bk_gray [] (q/background 255))
(defn- circle [x y r]
  (q/ellipse x y r r))
(defn- half [x]
  (/ x 2))
(defn- spir [rad radi p sincos]
  (+ p (* radi (sincos rad))))
(defn- spir-x [rad radi x]
  (spir rad radi x q/cos))
(defn- spir-y [rad radi y]
  (spir rad radi y q/sin))
(defn- my-zip [xs ys]
  (map #(list %1 %2 %3 %4) xs ys (rest xs) (rest ys)))
(def MAX Integer/MAX_VALUE)
(defn setup []
  (bk_gray)
  (q/stroke-weight 0.5)
  (q/smooth)
  (let [d 360
        radius 100
        cent-x (half (q/width))
        cent-y (half (q/height))]
  (dotimes [_ 100]
    (let [start-angle (rand d)
          end-angle (+ (* 4 d) (rand (* 4 d)))
          angle-step (+ 5 (rand 3))
          rad-noise (map #(* 200 (q/noise %)) (range (rand 10) MAX 0.05))
          rads (map q/radians (range start-angle end-angle angle-step))
          radi (range 10 (* 5 d) 0.5)
          radii (map #(+ %1 %2 -100) radi rad-noise)
          xs (map #(spir-x %1 %2 cent-x) rads radii)
          ys (map #(spir-y %1 %2 cent-y) rads radii)
          line-args (my-zip xs ys)
          ]
      (q/stroke (rand 20) (rand 50) (rand 70) 80)
      (dorun (map #(apply q/line %) line-args)))))
    (q/save-frame "gen.4.1.3.1.jpg"))
(q/defsketch gen_4_1_3_1
  :title "100 of Noisy Spiral"
  :setup setup
  :size [500 300]
  )

