# Tubelight Effect (Jetpack Compose)

<img width="1200" height="630" alt="Tubelight Effect from Local Spark (1)" src="https://github.com/user-attachments/assets/415414f1-f9c3-4730-aed1-5ef3066e39bf" />

A visual **tube light / glow effect experiment** built with **Jetpack Compose**.  
This project demonstrates how to create complex lighting and glow effects using
`sweepGradient`, `BlendMode`, masking, and animated parameters.

The effect is inspired by neon / tube-light visuals often used in futuristic UI,
search bars, cursors, and ambient animations.

---

## ✨ Features

- Sweep gradient–based glow rendering
- Dual-layer glow (main + secondary color)
- Vertical alpha mask for smooth fade-out
- Mirrored rendering for symmetrical light
- Smooth animated appearance and expansion
- Fully implemented in **Jetpack Compose Canvas**

---

## 🧠 Core Concepts Used

- `drawWithCache`
- `Brush.sweepGradient`
- `Brush.verticalGradient`
- `BlendMode.Plus`
- `BlendMode.DstIn` (masking)
- `graphicsLayer` with `CompositingStrategy.Offscreen`
- Custom easing (`easeIn`)
- Compose animation APIs (`animateFloatAsState`)

---

# Tubelight Effect (Jetpack Compose)

A visual experiment implementing a **tube light / glow effect** using **Jetpack Compose**.  
This project demonstrates how to create complex light effects with `sweepGradient`, blend modes, masking, and smooth animations.

The effect imitates a neon / tube light that expands, glows, and fades with animated progress.

---

## ✨ Features

- Sweep gradient–based glow effect
- Dual-layer glow (main glow + secondary soft glow)
- Vertical fade mask using `BlendMode.DstIn`
- Mirrored rendering for symmetric tube light
- Smooth animation with `animateFloatAsState`
- Fully written in Jetpack Compose (no Canvas API directly)

---

## 🧠 How It Works

### Core Idea

The tube light is composed of **two mirrored halves**, each rendered using a `Light` composable.

Each half:
- Draws **two sweep gradients** for depth and intensity
- Uses **additive blending (`BlendMode.Plus`)** to enhance brightness
- Applies a **vertical alpha mask** to fade the light naturally
- Can be horizontally flipped to create symmetry

---

## 🧩 Main Components

### `TubelightEffect`

Controls:
- Animation state (`clicked`)
- Animation progress (`animateFloatAsState`)
- Tube width expansion
- Vertical start position

It renders:
- A title
- A start button
- Two `Light` composables (left & right)

---

### `Light`

Responsible for rendering the glow:

- `sweepGradient` for radial light spread
- `BlendMode.Plus` for glow accumulation
- `BlendMode.DstIn` for vertical fading mask
- `graphicsLayer` with `CompositingStrategy.Offscreen`
- Optional horizontal mirroring (`flip`)

---

## 🎛️ Customization Parameters

`Light` composable supports:

```kotlin
mainGlowColor: Color
glowColor: Color
flip: Boolean
startY: Float
progress: Float
halfTubeWidth: Float
```

![Ezgif 574x780](https://github.com/user-attachments/assets/bd1ae1d9-6854-4d0f-9c38-254465cdaacd)
