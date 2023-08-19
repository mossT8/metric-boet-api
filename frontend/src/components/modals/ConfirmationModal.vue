<template>
    <div class="modal-overlay" @click.self="$emit('cancel')">
        <div class="modal">
            <div class="modal-header">
                <h2>{{ title }}</h2>
            </div>
            <div class="modal-body">
                <slot></slot>
            </div>
            <div class="modal-footer">
                <button class="btn btn-secondary" @click="$emit('cancel')">Cancel</button>
                <button class="btn btn-primary" @click="$emit('confirm')">Confirm</button>
            </div>
        </div>
    </div>
</template>
  
<script>
// vue
import { defineComponent } from 'vue'

const ConfirmationModal = defineComponent({
    name: 'confirmation-modal',
    props: {
        title: {
            type: String,
            default: "Confirmation",
        },
    },
});

export default ConfirmationModal;
</script>
  
<style scoped lang="scss">
.modal-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;

    .modal {
        background-color: white;
        padding: 1rem;
        border-radius: 4px;
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.3);
        width: 90%;
        max-width: 30rem;
    }

    .modal-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 1rem;
    }

    .modal-header h2 {
        margin: 0;
    }

    .modal-footer {
        display: flex;
        justify-content: flex-end;
        margin-top: 1rem;
    }

    .btn {
        padding: 0.5rem 1rem;
        border-radius: 4px;
        border: none;
        cursor: pointer;
        font-size: 1rem;
        font-weight: bold;
        transition: background-color 0.3s ease;
    }

    .btn-secondary {
        background-color: #f0f0f0;
        color: #333;
        margin-right: 1rem;
    }

    .btn-primary {
        background-color: #3f51b5;
        color: white;
    }

    .btn:hover {
        background-color: rgba(0, 0, 0, 0.1);
    }
}
</style>
  