(ns generative_art_tut.gen_4_1_4
  (:require [quil.core :as q]))

;;;;; Processing用のソース;;;;;
; void setup() {
;   size(500, 300);
;   background(255);
;   strokeWeight(5);
;   smooth();
;   
;   float radius = 100;
;   int centx = 250;
;   int centy = 150;
; 
;   stroke(0, 30);
;   noFill();
;   ellipse(centx, centy, radius*2, radius*2);
;   
;   stroke(20, 50, 70);
;   strokeWeight(1);
;   
;   float x, y;
;   float noiseval = random(10);
;   float radVariance, thisRadius, rad;
;   beginShape();
;   fill(20, 50,70, 50);
;   for (float ang = 0; ang <= 360; ang += 1) {
;     
;     noiseval += 0.1;
;     radVariance = 30 * customNoise(noiseval);
;     
;     thisRadius = radius + radVariance;
;     rad = radians(ang);
;     x = centx + (thisRadius * cos(rad));
;     y = centy + (thisRadius * sin(rad));
;     
;     curveVertex(x, y);
;   }
;   endShape();
; }
; 
; float customNoise(float value) {
;   float retValue = pow(sin(value), 3);
;   return retValue;
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
(def MAX_FLOAT Float/POSITIVE_INFINITY)
(defn- custom-noise [x]
  (q/pow (q/sin x) 3))

(defn setup []
  (bk_gray)
  (q/stroke-weight 5)
  (q/smooth)

  (let [d 360
        radius 100
        cent-x (half (q/width))
        cent-y (half (q/height))
        noise-val (rand 10)
        rads (map q/radians (range 0 d))
        noise-vals (range noise-val MAX_FLOAT 0.1)
        rad-vars (map #(* 30 (custom-noise %)) noise-vals)
        radii (map + rad-vars (repeat radius))
        xs (map #(spir-x %1 %2 cent-x) rads radii)
        ys (map #(spir-y %1 %2 cent-y) rads radii)
        ]
    (q/stroke 0 30)
    (q/no-fill)
    (circle cent-x cent-y (* 2 radius))
    (q/stroke 20 50 70)
    (q/stroke-weight 1)
    (q/begin-shape)
    (q/fill 20 50 70 50)
    (dorun (map q/curve-vertex xs ys))
    (q/end-shape))
  (q/save-frame "gen.4.1.4.jpg"))
(q/defsketch gen_4_1_4
  :title "custome Noise Circle"
  :setup setup
  :size [500 300]
  )

