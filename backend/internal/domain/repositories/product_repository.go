package repositories

import (
	"github.com/google/uuid"
	"github.com/mossT8/backend/internal/domain/entities"
)

type ProductRepository interface {
	Create(product *entities.ValidatedProduct) (*entities.Product, error)
	FindById(id uuid.UUID) (*entities.Product, error)
	FindAll() ([]*entities.Product, error)
	Update(product *entities.ValidatedProduct) (*entities.Product, error)
	Delete(id uuid.UUID) error
}
