package cn.imadc.application.skeleton.basic.rbac.permission.util;

import cn.imadc.application.base.data.structure.tree.Tree;
import cn.imadc.application.base.data.structure.tree.TreeNode;
import cn.imadc.application.skeleton.basic.rbac.permission.entity.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 权限工具类
 * </p>
 *
 * @author 杜劲松
 * @since 2022-07-21
 */
public class PermissionUtil {

    /**
     * 权限工具类
     *
     * @param permissions 权限
     * @return 权限树
     */
    public static List<TreeNode> tree(List<Permission> permissions) {
        List<TreeNode> treeNodes = new ArrayList<>();
        for (Permission permission : permissions) {
            TreeNode treeNode = new TreeNode();
            treeNode.setId(permission.getId());
            treeNode.setParentId(permission.getParentId());
            treeNode.setName(permission.getName());
            treeNode.setIcon(permission.getIcon());
            treeNodes.add(treeNode);
        }

        return new Tree(treeNodes).buildTree(0L);
    }
}
