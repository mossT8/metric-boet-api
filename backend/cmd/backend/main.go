package main

import (
	"log"

	_ "github.com/jinzhu/gorm/dialects/postgres"
	"github.com/labstack/echo/v4"
	"github.com/mossT8/backend/internal/application/services"
	postgres2 "github.com/mossT8/backend/internal/infrastructure/db/postgres"
	"github.com/mossT8/backend/internal/interface/api/rest"
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

func main() {
	dsn := "host=localhost user=postgres password=password dbname=metricboetdb port=5432 sslmode=disable TimeZone=Asia/Shanghai"
	port := ":8080"

	gormDB, err := gorm.Open(postgres.Open(dsn), &gorm.Config{})
	if err != nil {
		log.Fatalf("Failed to connect to database: %v", err)
	}

	// err = gormDB.AutoMigrate(postgres2.Product{}, postgres2.Seller{})
	// if err != nil {
	// 	log.Fatalf("Failed to migrate database: %v", err)
	// }

	productRepo := postgres2.NewGormProductRepository(gormDB)
	sellerRepo := postgres2.NewGormSellerRepository(gormDB)

	productService := services.NewProductService(productRepo, sellerRepo)
	sellerService := services.NewSellerService(sellerRepo)

	e := echo.New()
	rest.NewProductController(e, productService)
	rest.NewSellerController(e, sellerService)

	if err := e.Start(port); err != nil {
		log.Fatalf("Failed to start server: %v", err)
	}
}
