<template>
  <div class="editor">
    <div class="editor-header">
      {{ heading }}
    </div>
    <div class="menubar">
      <span v-for="actionName in activeButtons" :key="actionName">
        <button v-if="actionName === 'bold'" class="menubar__button" :class="{ 'is-active': editor.isActive('bold') }"
          @click="editor.chain().focus().toggleBold().run()">
          <icon name="bold" />
        </button>
        <button v-if="actionName === 'italic'" class="menubar__button" :class="{ 'is-active': editor.isActive('italic') }"
          @click="editor.chain().focus().toggleItalic().run()">
          <icon name="italic" />
        </button>

        <button v-if="actionName === 'strike'" class="menubar__button" :class="{ 'is-active': editor.isActive('strike') }"
          @click="editor.chain().focus().toggleStrike().run()">
          <icon name="strike" />
        </button>

        <button v-if="actionName === 'underline'" class="menubar__button"
          :class="{ 'is-active': editor.isActive('underline') }" @click="editor.chain().focus().toggleUnderline().run()">
          <icon name="underline" />
        </button>

        <button v-if="actionName === 'code'" class="menubar__button" :class="{ 'is-active': editor.isActive('code') }"
          @click="editor.chain().focus().toggleCode().run()">
          <icon name="code" />
        </button>

        <button v-if="actionName === 'h1'" class="menubar__button"
          :class="{ 'is-active': editor.isActive('heading', { level: 1 }) }"
          @click="editor.chain().focus().toggleHeading({ level: 1 }).run()">
          H1
        </button>

        <button v-if="actionName === 'h2'" class="menubar__button"
          :class="{ 'is-active': editor.isActive('heading', { level: 2 }) }"
          @click="editor.chain().focus().toggleHeading({ level: 2 }).run()">
          H2
        </button>

        <button v-if="actionName === 'h3'" class="menubar__button"
          :class="{ 'is-active': editor.isActive('heading', { level: 3 }) }"
          @click="editor.chain().focus().toggleHeading({ level: 3 }).run()">
          H3
        </button>

        <button v-if="actionName === 'bulletList'" class="menubar__button"
          :class="{ 'is-active': editor.isActive('bulletList') }"
          @click="editor.chain().focus().toggleBulletList().run()">
          <icon name="ul" />
        </button>

        <button v-if="actionName === 'orderedList'" class="menubar__button"
          :class="{ 'is-active': editor.isActive('orderedList') }"
          @click="editor.chain().focus().toggleOrderedList().run()">
          <icon name="ol" />
        </button>

        <button v-if="actionName === 'blockquote'" class="menubar__button"
          :class="{ 'is-active': editor.isActive('blockquote') }"
          @click="editor.chain().focus().toggleBlockquote().run()">
          <icon name="quote" />
        </button>

        <button v-if="actionName === 'codeBlock'" class="menubar__button"
          :class="{ 'is-active': editor.isActive('codeBlock') }" @click="editor.chain().focus().toggleCodeBlock().run()">
          <icon name="code" />
        </button>

        <button v-if="actionName === 'horizontalRule'" class="menubar__button"
          @click="editor.chain().focus().setHorizontalRule().run()">
          <icon name="hr" />
        </button>

        <button v-if="actionName === 'undo'" class="menubar__button" @click="editor.chain().focus().undo().run()">
          <icon name="undo" />
        </button>

        <button v-if="actionName === 'redo'" class="menubar__button" @click="editor.chain().focus().redo().run()">
          <icon name="redo" />
        </button>
      </span>
    </div>
    <hr />
    <editor-content class="editor__content" :initialContent="initialContent" :editor="editor" />
    <div class="editor-footer">
      <button class="btn btn-danger mr-1" :disabled="loading" @click="onClear">
        Clear
      </button>
      <button class="btn btn-primary" :disabled="loading" @click="onSubmit">
        <span v-show="loading" class="spinner-border spinner-border-sm"></span>
        {{ btnTxt }}
      </button>
    </div>
  </div>
</template>

<script>
import Icon from '@/core/editor/Icon.vue';
import { Editor, EditorContent } from '@tiptap/vue-3';
import StarterKit from '@tiptap/starter-kit';
import Underline from '@tiptap/extension-underline';

export default {
  name: 'Editor',
  components: {
    EditorContent,
    Icon
  },
  props: {
    initialContent: {
      type: String,
      required: true,
      default: '<em>editable text</em>',
    },
    heading: {
      type: String,
      required: false,
      default: 'Text Editor',
    },
    activeButtons: {
      type: Array,
      validator: function (list) {
        for (let el of list) {
          // The value must match one of these strings
          if (
            [
              'bold',
              'italic',
              'strike',
              'underline',
              'code',
              'h1',
              'h2',
              'h3',
              'bulletList',
              'orderedList',
              'blockquote',
              'codeBlock',
              'horizontalRule',
              'undo',
              'redo',
            ].indexOf(el) === -1
          ) {
            return -1;
          }
        }
        return 1;
      },
      default: () => [
        'bold',
        'italic',
        'strike',
        'underline',
        'h1',
        'h2',
        'h3',
        'bulletList',
        'orderedList',
        'blockquote',
        'codeBlock',
        'horizontalRule',
        'undo',
        'redo',
      ],
    },
  },
  emits: ['update'],
  computed: {
    btnTxt() {
      return this.loading ? 'Updating...' : 'Update';
    }
  },
  data() {
    return {
      html: '',
      json: '',
      editor: null,
      loading: false,
    };
  },
  created() {
    this.onClear();

    this.editor = new Editor({
      content: this.initialContent,
      extensions: [StarterKit, Underline],
    });
    this.html = this.editor.getHTML();
    this.json = this.editor.getJSON();


    this.editor.on('update', () => {
      this.html = this.editor.getHTML();
      this.json = this.editor.getJSON();
      this.$emit('update', this.html);
    });
  },
  methods: {
    onSubmit() {
      this.$emit('onSubmit', this.html);
    },
    onClear() {
      if (this.editor) {
        this.editor.commands.clearContent(true);
      }
    }
  },
  beforeUnmount() {
    this.editor.destroy();
  },
};
</script>

<style lang="scss" scoped></style>
