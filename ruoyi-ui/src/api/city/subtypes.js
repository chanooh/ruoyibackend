import request from '@/utils/request'

// 查询工单子类型列表
export function listSubtypes(query) {
  return request({
    url: '/city/subtypes/list',
    method: 'get',
    params: query
  })
}

// 查询工单子类型详细
export function getSubtypes(id) {
  return request({
    url: '/city/subtypes/' + id,
    method: 'get'
  })
}

// 新增工单子类型
export function addSubtypes(data) {
  return request({
    url: '/city/subtypes',
    method: 'post',
    data: data
  })
}

// 修改工单子类型
export function updateSubtypes(data) {
  return request({
    url: '/city/subtypes',
    method: 'put',
    data: data
  })
}

// 删除工单子类型
export function delSubtypes(id) {
  return request({
    url: '/city/subtypes/' + id,
    method: 'delete'
  })
}
