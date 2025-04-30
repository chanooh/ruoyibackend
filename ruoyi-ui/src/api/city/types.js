import request from '@/utils/request'

// 查询工单类型列表
export function listTypes(query) {
  return request({
    url: '/city/types/list',
    method: 'get',
    params: query
  })
}

// 查询工单类型详细
export function getTypes(id) {
  return request({
    url: '/city/types/' + id,
    method: 'get'
  })
}

// 新增工单类型
export function addTypes(data) {
  return request({
    url: '/city/types',
    method: 'post',
    data: data
  })
}

// 修改工单类型
export function updateTypes(data) {
  return request({
    url: '/city/types',
    method: 'put',
    data: data
  })
}

// 删除工单类型
export function delTypes(id) {
  return request({
    url: '/city/types/' + id,
    method: 'delete'
  })
}
