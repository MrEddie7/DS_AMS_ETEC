#include <Wire.h>
#include <Adafruit_GFX.h>
#include <Adafruit_SSD1306.h>
#include <WiFi.h>
#include <WiFiClientSecure.h>
#include <HTTPClient.h>

// ----------------- CONFIGURAÇÕES DO DISPLAY -----------------
#define SCREEN_WIDTH 128
#define SCREEN_HEIGHT 64
Adafruit_SSD1306 display(SCREEN_WIDTH, SCREEN_HEIGHT, &Wire, -1);

// ----------------- CONFIGURAÇÕES WIFI -----------------
const char* ssid = "Teste"; // Troque pelo nome da sua rede WiFi
const char* password = "123456789"; // Troque pela senha da WiFi

// ----------------- CONFIGURAÇÃO DA API -----------------
const char* server = "https://painel-solar-api.onrender.com/dados";

// ----------------- CONFIGURAÇÃO DO LDR -----------------
#define LDR_PIN 34 // Pino ADC do ESP32
float energiaFator = 0.5; // Multiplicador fictício para energia

void setup() {
  Serial.begin(115200);
  Wire.begin(21, 22); // SDA=21, SCL=22

  // Conexão WiFi
  Serial.println("🔄 Conectando ao WiFi...");
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("\n✅ WiFi conectado!");

  // Inicializar Display
  if (!display.begin(SSD1306_SWITCHCAPVCC, 0x3C)) {
    Serial.println("❌ Erro: Display não encontrado!");
    while (true);
  }
  display.clearDisplay();
  display.setTextSize(1);
  display.setTextColor(SSD1306_WHITE);
  display.setCursor(0, 0);
  display.println("Sistema iniciado...");
  display.display();
  delay(1000);
}

void loop() {
  // ----------------- Ler LDR -----------------
  int ldrRaw = analogRead(LDR_PIN); // 0-4095
  float lux = map(ldrRaw, 0, 4095, 0, 10000); // Ajuste máximo 10.000 lx
  float energia = lux * energiaFator;

  Serial.printf("📡 Luminosidade: %.2f lx | Energia: %.2f W\n", lux, energia);

  // ----------------- Mostrar no Display -----------------
  display.clearDisplay();
  display.setCursor(0, 0);
  display.print("Luminosidade: ");
  display.println(lux);
  display.print("Energia: ");
  display.print(energia);
  display.println(" W");
  display.display();

  // ----------------- Esperar 2 segundos antes de enviar -----------------
  delay(2000);

  // ----------------- Enviar para API -----------------
  if (WiFi.status() == WL_CONNECTED) {
    WiFiClientSecure client;
    client.setInsecure(); // Aceita certificados HTTPS inseguros

    HTTPClient http;
    http.begin(client, server);
    http.addHeader("Content-Type", "application/json");

    String json = "{\"radiacao\":" + String(lux) + ",\"energia\":" + String(energia) + "}";
    int httpResponseCode = http.POST(json);

    if (httpResponseCode > 0) {
      Serial.printf("✅ Dados enviados! Código HTTP: %d\n", httpResponseCode);
    } else {
      Serial.printf("❌ Erro ao enviar. Código: %d\n", httpResponseCode);
    }

    http.end();
  } else {
    Serial.println("⚠️ WiFi desconectado. Não foi possível enviar dados.");
  }

  // ----------------- Esperar o restante do tempo (4 segundos) -----------------
  delay(4000); // 2s + 4s = 6s total
}
