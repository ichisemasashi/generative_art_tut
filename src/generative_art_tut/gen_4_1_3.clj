(ns generative_art_tut.gen_4_1_3
  (:require [quil.core :as q]))

;;;;; Processing用のソース;;;;;
; size(500, 300);
; background(255);
; strokeWeight(5);
; smooth();
; 
; float radius = 100;
; int centx = 250;
; int centy = 150;
; 
; stroke(0, 30);
; noFill();
; ellipse(centx, centy, radius * 2, radius * 2);
; 
; stroke(20,50, 70);
; radius = 10;
; float x, y;
; float lastx = -999;
; float lasty = -999;
; float radiusNoise = random(10);
; for (float ang = 0;ang<=1440;ang+=5) {
;   radiusNoise += 0.05;
;   radius += 0.5;
;   float thisRadius = radius + (noise(radiusNoise) * 200) - 100;
;   float rad = radians(ang);
;   x = centx + (thisRadius * cos(rad));
;   y = centy + (thisRadius * sin(rad));
;   if(lastx > -999) {
;     line(x, y, lastx, lasty);
;   }
;   lastx = x;
;   lasty = y;
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
  (q/stroke-weight 5)
  (q/smooth)
  (let [radius 100
        cent-x (half (q/width))
        cent-y (half (q/height))
        rad-noise (map #(* 200 (q/noise %)) (range (rand 10) MAX 0.05))
        rads (map q/radians (range 0 (* 360 4) 5))
        radi (range 10 2000 0.5)
        radii (map #(+ %1 %2 -100) radi rad-noise)
        xs (map #(spir-x %1 %2 cent-x) rads radii)
        ys (map #(spir-y %1 %2 cent-y) rads radii)
        line-args (my-zip xs ys)
        ]
    (q/stroke 0 30)
    (q/no-fill)
    (circle cent-x cent-y (* 2 radius))
    (q/stroke 20 50 70)
    (dorun (map #(apply q/line %) line-args)))
  (q/save-frame "gen.4.1.3.jpg"))

(q/defsketch gen_4_1_3
  :title "Noisy Spiral"
  :setup setup
  :size [500 300]
  )

