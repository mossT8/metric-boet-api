package interfaces

import (
	"github.com/google/uuid"
	"github.com/mossT8/backend/internal/application/command"
	"github.com/mossT8/backend/internal/application/query"
)

type ProductService interface {
	CreateProduct(productCommand *command.CreateProductCommand) (*command.CreateProductCommandResult, error)
	FindAllProducts() (*query.ProductQueryListResult, error)
	FindProductById(id uuid.UUID) (*query.ProductQueryResult, error)
}
