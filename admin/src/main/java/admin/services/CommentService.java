package admin.services;

import admin.forms.comment.CommentDeleteForm;
import admin.forms.comment.CommentRegisterForm;
import admin.forms.comment.CommentUpdateForm;
import admin.responses.CommentDeleteFormResponse;
import admin.responses.CommentRegisterFormResponse;
import admin.responses.CommentTopResponse;
import admin.responses.CommentUpdateFormResponse;

public interface CommentService {
    /**
     * 一覧
     *
     * @return
     * @param page
     * @param limit
     */
    CommentTopResponse listing(int offset, int limit);

    /**
     * 登録フォーム
     *
     * @return
     */
    CommentRegisterFormResponse registerForm();

    /**
     * 登録
     *
     * @param commentRegisterForm
     */
    void create(CommentRegisterForm commentRegisterForm);

    /**
     * 更新フォーム
     *
     * @param commentId
     * @return
     */
    CommentUpdateFormResponse updateFormResponse(Long commentId);

    /**
     * 更新
     *
     * @param commentUpdateForm
     */
    void update(CommentUpdateForm commentUpdateForm);

    /**
     * 削除フォーム
     *
     * @param commentId
     * @return
     */
    CommentDeleteFormResponse deleteForm(Long commentId);

    /**
     * 削除
     *
     * @param commentDeleteForm
     */
    void delete(CommentDeleteForm commentDeleteForm);

    CommentUpdateForm updateForm(Long commentId);
}
