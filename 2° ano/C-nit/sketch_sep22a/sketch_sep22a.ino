#include <WiFi.h>
#include <HTTPClient.h>
#include <WiFiClientSecure.h>
#include <Adafruit_GFX.h>
#include <Adafruit_SSD1306.h>
#include <Adafruit_I2CDevice.h>


#define LDR_PIN 34

const char* ssid = "Wokwi-GUEST";
const char* password = "";
const char* server = "https://painel-solar-api.onrender.com/dados";

Adafruit_SSD1306 display(128, 64, &Wire, -1);

// Função para garantir que está conectado
void verificarWiFi() {
  if (WiFi.status() != WL_CONNECTED) {
    Serial.print("Reconectando ao WiFi");
    WiFi.disconnect();
    WiFi.begin(ssid, password);
    int tentativas = 0;
    while (WiFi.status() != WL_CONNECTED && tentativas < 20) {
      delay(500);
      Serial.print(".");
      tentativas++;
    }
    if (WiFi.status() == WL_CONNECTED) {
      Serial.println(" Reconectado!");
    } else {
      Serial.println(" Falha ao reconectar.");
    }
  }
}

void setup() {
  Serial.begin(115200);
  pinMode(LDR_PIN, INPUT);

  WiFi.begin(ssid, password);
  Serial.print("Conectando ao WiFi");
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println(" Conectado!");

  if (!display.begin(SSD1306_SWITCHCAPVCC, 0x3C)) {
    Serial.println("Display não encontrado");
    while (true);
  }
  display.clearDisplay();
}

void loop() {
  verificarWiFi();

  int valorLDR = analogRead(LDR_PIN);
  float energia = map(valorLDR, 0, 4095, 0, 1000);

  Serial.printf("Radiação: %d - Energia: %.2f W\n", valorLDR, energia);

  display.clearDisplay();
  display.setTextSize(1);
  display.setTextColor(SSD1306_WHITE);
  display.setCursor(0, 10);
  display.print("Radiação: ");
  display.println(valorLDR);
  display.print("Energia: ");
  display.print(energia);
  display.print(" W");
  display.display();

  if (WiFi.status() == WL_CONNECTED) {
    WiFiClientSecure client;
    client.setInsecure();  

    HTTPClient http;
    http.begin(client, server);
    http.addHeader("Content-Type", "application/json");

    String json = "{\"radiacao\":" + String(valorLDR) + ",\"energia\":" + String(energia) + "}";
    int httpResponseCode = http.POST(json);

    if (httpResponseCode > 0) {
      Serial.printf("✅ Dados enviados! Código: %d\n", httpResponseCode);
    } else {
      Serial.printf("❌ Falha no envio. Código: %d - ", httpResponseCode);
      if (httpResponseCode == -1) {
        Serial.println("Erro de conexão (timeout ou rede).");
      } else {
        Serial.println("Erro desconhecido.");
      }
    }

    http.end();
  } else {
    Serial.println("⚠️ WiFi desconectado. Não foi possível enviar dados.");
  }

  delay(5000);
}
